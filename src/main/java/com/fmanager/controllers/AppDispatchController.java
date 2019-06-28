package main.java.com.fmanager.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import main.java.com.fmanager.exception.FmanagerRestException;
import main.java.com.fmanager.models.AppDispatch;
import main.java.com.fmanager.models.AppTypes;
import main.java.com.fmanager.services.AppDispatchService;
import main.java.com.fmanager.utils.ConstantString;
import main.java.com.fmanager.utils.ErrorNumber;

@RestController
@RequestMapping("/appdispatch")
public class AppDispatchController {

	@Resource
	private AppDispatchService appDispatchService;

	@RequestMapping(value = "/updateapp", method = RequestMethod.POST)
	@RequiresRoles("user")
	public AppDispatch uploadApp(@RequestParam("app") MultipartFile file) throws FmanagerRestException {

		if (file.getOriginalFilename().endsWith(ConstantString.APK_FILE_SUFFIX) || file.getOriginalFilename().endsWith(ConstantString.IOS_FILE_SUFFIX)) {
			return appDispatchService.parseApp(file);
		}

		throw new FmanagerRestException(ErrorNumber.FILE_TYPE_ERROR, "unknow type of app");
	}

	@RequestMapping(value = "/myapps", method = RequestMethod.GET)
	@RequiresRoles("user")
	public List<AppDispatch> getMyApps() {

		return appDispatchService.getAppList();
	}
	
	
	@RequestMapping(value = "/apptypes", method = RequestMethod.GET)
	public Map<Integer,String> getAppTypes(){
		Map<Integer,String> types = new HashMap<>();
		for (AppTypes t : AppTypes.values()) { 
			types.put(t.getType(), t.name());
		}
		return types;
	}
	
	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	@RequiresRoles("user")
	public AppDispatch getAppDetail(@PathVariable long id) {

		return null;
	}
	
}
