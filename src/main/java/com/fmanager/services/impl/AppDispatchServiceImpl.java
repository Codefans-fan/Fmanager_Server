package main.java.com.fmanager.services.impl;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import com.dd.plist.NSArray;
import com.dd.plist.NSDictionary;
import com.dd.plist.PropertyListFormatException;
import com.dd.plist.PropertyListParser;

import main.java.com.fmanager.dao.AppDispatchDAO;
import main.java.com.fmanager.exception.FmanagerRestException;
import main.java.com.fmanager.models.AppDispatch;
import main.java.com.fmanager.models.AppTypes;
import main.java.com.fmanager.models.AppVersion;
import main.java.com.fmanager.services.AppDispatchService;
import main.java.com.fmanager.utils.ConstantString;
import main.java.com.fmanager.utils.ErrorNumber;
import main.java.com.fmanager.utils.JwtTokenUtil;
import main.java.com.fmanager.utils.Utils;
import net.dongliu.apk.parser.ApkFile;
import net.dongliu.apk.parser.bean.ApkMeta;
import net.dongliu.apk.parser.bean.IconFace;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

@Service
public class AppDispatchServiceImpl implements AppDispatchService {

	@Value("${fmanager.file.resources}")
	private String fileRootPath;

	@Resource
	private AppDispatchDAO appDispatchDAO;
	
	
	
	@Override
	public AppDispatch parseApp(MultipartFile appFile) throws FmanagerRestException {

		AppDispatch app = parseAppFile(appFile);
		appDispatchDAO.insertApp(app);
		return app;
	}
	
	
	private String getIconFilePath(String zipExPath,String iconName) {
		File zipFiles = new File(zipExPath);
		File[] matchingFiles = zipFiles.listFiles(new FilenameFilter() {
		    public boolean accept(File dir, String name) {
		        return name.startsWith(iconName);
		    }
		});
		return matchingFiles[0].getAbsolutePath();
	}

	@Override
	public List<AppDispatch> getAppList() {
		return appDispatchDAO.getAppDispatchByUserId(JwtTokenUtil.getUserIdFromAuthPrincipal());
	}


	@Override
	public AppDispatch getAppDetail(long id) throws FmanagerRestException {
		
		long userId = JwtTokenUtil.getUserIdFromAuthPrincipal();
		
		AppDispatch dispath =  appDispatchDAO.getAppDetail(id);
		
		//check records is belong to login user
		if(dispath.getUserId() != userId) {
			throw new FmanagerRestException(ErrorNumber.RECORD_NOT_FOUND, "Record not found");
		}
		return dispath;
	}


	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public AppDispatch newAppVersion(MultipartFile file, AppDispatch oldInstance) throws FmanagerRestException {
		
		long userId = JwtTokenUtil.getUserIdFromAuthPrincipal();
	
		//check records is belong to login user
		if(oldInstance.getUserId() != userId) {
			throw new FmanagerRestException(ErrorNumber.RECORD_NOT_FOUND, "Record not found");
		}
		
		
		 AppDispatch newApp = parseAppFile(file);
		 
		 if(newApp.getAppType() != oldInstance.getAppType()) {
			 throw new FmanagerRestException(ErrorNumber.FILE_TYPE_ERROR, "File type error");
		 }
		 
		 //update app data
		 newApp.setId(oldInstance.getId());
		 newApp.setVersion(oldInstance.getVersion()+1);
		 newApp.setDownloadCount(oldInstance.getDownloadCount());
		 
		 //move old data to version tables
		 AppVersion v = new AppVersion();
		 v.setAppFile(oldInstance.getAppFile());
		 v.setDownloadCount(oldInstance.getDownloadCount());
		 v.setVersion(oldInstance.getVersion());
		 
		 
		 appDispatchDAO.insertNewVersion(v);
		 appDispatchDAO.updateAppDispatch(newApp);
		 
		return newApp;
	}

	
	
	private AppDispatch parseAppFile(MultipartFile appFile) throws FmanagerRestException {
		// ApkFile apkFile = new ApkFile(appFile.get)
				String uuFileName = Utils.generateFileName(appFile.getOriginalFilename());
				String fileName = fileRootPath + File.separator + uuFileName;
				File outFile = new File(fileName);
				try {
					FileUtils.touch(outFile);
					appFile.transferTo(outFile);
					AppDispatch appdisp = new AppDispatch();
					if(fileName.endsWith(ConstantString.APK_FILE_SUFFIX)) {
						ApkFile app = new ApkFile(outFile);
						ApkMeta apkMeta = app.getApkMeta();
						appdisp.setAppName(apkMeta.getLabel());
						appdisp.setAppVersion(apkMeta.getVersionName());
						appdisp.setAppType(AppTypes.Android.getType());
						appdisp.setAppBundle(apkMeta.getPackageName());
						appdisp.setAppId(String.valueOf(System.currentTimeMillis()));
						appdisp.setAppFile(uuFileName);
						appdisp.setUserId(JwtTokenUtil.getUserIdFromAuthPrincipal());
						List<IconFace> icons = app.getAllIcons();
						
						if(icons.size() > 0) {
							String iconFileName = Utils.generateFileName(FilenameUtils.getName(icons.get(0).getPath()));
							String iconFilePath = fileRootPath + File.separator + ConstantString.ICONS_FOLDER_NAME+ File.separator+ iconFileName;
							File iconFile = new File(iconFilePath);
							FileUtils.touch(iconFile);
							FileUtils.writeByteArrayToFile(iconFile, icons.get(0).getData());
							appdisp.setCombo(iconFileName);
						}
						app.close();
						return appdisp;
						
					}else if(fileName.endsWith(ConstantString.IOS_FILE_SUFFIX)){
						//ios file
						String tmpFolder = fileRootPath + File.separator + "tmp" + File.separator + UUID.randomUUID().toString();
						ZipFile zipFile = new ZipFile(fileName);
						zipFile.extractAll(tmpFolder);
						
						// Fetch version information
						String appTempPath = new File(tmpFolder + File.separator + "Payload").listFiles()[0].getAbsolutePath();
						NSDictionary dictionary = (NSDictionary) PropertyListParser.parse(new File(appTempPath + File.separator + "Info.plist"));
						appdisp.setAppName(dictionary.get("CFBundleDisplayName").toString());
						appdisp.setAppBundle(dictionary.get("CFBundleIdentifier").toString());
						appdisp.setAppId(String.valueOf(System.currentTimeMillis()));
						appdisp.setAppVersion(dictionary.get("CFBundleShortVersionString").toString());
						appdisp.setAppType(AppTypes.IOS.getType());
						appdisp.setAppFile(uuFileName);
						appdisp.setUserId(JwtTokenUtil.getUserIdFromAuthPrincipal());
						 
						NSDictionary iconDict = (NSDictionary) dictionary.get("CFBundleIcons");
						
						if(iconDict.size() > 0) {
							if(iconDict.containsKey("CFBundlePrimaryIcon")){
		                        NSDictionary CFBundlePrimaryIcon = (NSDictionary)iconDict.get("CFBundlePrimaryIcon"); 
		                            if(CFBundlePrimaryIcon.containsKey("CFBundleIconFiles")){
		                            	NSArray CFBundleIconFiles =(NSArray)CFBundlePrimaryIcon.get("CFBundleIconFiles"); 
		                            	String icon = CFBundleIconFiles.getArray()[0].toString();
		                            	String iconFileName = Utils.generateFileName(FilenameUtils.getName(icon));
		                            	String iconFilePath  = fileRootPath + File.separator + ConstantString.ICONS_FOLDER_NAME+ File.separator+iconFileName ;
		                            	File iconFile = new File(iconFilePath);
		                            	FileUtils.copyFile(new File(getIconFilePath(appTempPath,icon)), iconFile);
		                            	appdisp.setCombo(iconFileName);
		                            }
							}
						}
						Files.walk(Paths.get(tmpFolder)).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
						return appdisp;
					}

				} catch (IOException | ZipException | PropertyListFormatException | ParseException | ParserConfigurationException | SAXException e) {
					throw new FmanagerRestException(ErrorNumber.FILE_SAVE_ERROR, "write app file error");
				}
				return null;
	}
}
