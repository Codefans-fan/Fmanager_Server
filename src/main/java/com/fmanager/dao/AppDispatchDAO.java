package main.java.com.fmanager.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import main.java.com.fmanager.models.AppDispatch;

@Mapper
public interface AppDispatchDAO {

	public void insertApp(AppDispatch app);

	public List<AppDispatch> getAppDispatchByUserId(long userId);
	
}
