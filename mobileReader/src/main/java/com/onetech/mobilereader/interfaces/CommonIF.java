package com.onetech.mobilereader.interfaces;

import com.onetech.mobilereader.entity.RequestEntity;
import com.onetech.mobilereader.entity.UserEntity;
import com.onetech.mobilereader.entityresult.BookResultEntity;
import com.onetech.mobilereader.entityresult.CategoryResultEntity;
import com.onetech.mobilereader.entityresult.LoginResultEntity;
import com.onetech.mobilereader.entityresult.RegisterResultEntity;
import com.onetech.mobilereader.entityresult.ServerConfigResultEntity;
import com.onetech.mobilereader.entityresult.UserResultEntity;

import retrofit.Callback;

public interface CommonIF {

	// Category store utils
	void getCategory(Callback<CategoryResultEntity> callback);

	void getServerConfig(RequestEntity request, Callback<ServerConfigResultEntity> callback);
	void saveUserEntity(UserEntity user);
	void searchBookName(String bookName, Callback<BookResultEntity> callback);
}
