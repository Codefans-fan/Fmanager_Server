package main.java.com.fmanager.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import main.java.com.fmanager.models.AppDispatch;
import main.java.com.fmanager.models.AppVersion;

@Mapper
public interface AppDispatchDAO {

	public void insertApp(AppDispatch app);

	public List<AppDispatch> getAppDispatchByUserId(long userId);

	public AppDispatch getAppDetail(long id);

	public void insertNewVersion(AppVersion v);
	
	public void updateAppDispatch(AppDispatch app);
}
