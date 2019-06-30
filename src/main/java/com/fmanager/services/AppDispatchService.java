package main.java.com.fmanager.services;


import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import main.java.com.fmanager.exception.FmanagerRestException;
import main.java.com.fmanager.models.AppDispatch;

public interface AppDispatchService {

	
	public AppDispatch parseApp(MultipartFile appfile)  throws FmanagerRestException;

	public List<AppDispatch> getAppList();

	public AppDispatch getAppDetail(long id) throws FmanagerRestException;

	/**
	 * 
	 * @param file  new version file
	 * @param oldInstance  can not be null
	 * @return   new version app obj
	 * @throws FmanagerRestException
	 */
	public AppDispatch newAppVersion(MultipartFile file, AppDispatch oldInstance) throws FmanagerRestException;
	
	
}
