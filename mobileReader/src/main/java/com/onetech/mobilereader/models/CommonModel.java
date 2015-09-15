package com.onetech.mobilereader.models;

import java.lang.reflect.Type;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.onetech.mobilereader.OTApplicationContext;
import com.onetech.mobilereader.configs.DBConfig;
import com.onetech.mobilereader.configs.DBConfig.Cache;
import com.onetech.mobilereader.entity.RequestEntity;
import com.onetech.mobilereader.entity.UserEntity;
import com.onetech.mobilereader.entityresult.BookResultEntity;
import com.onetech.mobilereader.entityresult.LoginResultEntity;
import com.onetech.mobilereader.entityresult.RegisterResultEntity;
import com.onetech.mobilereader.entityresult.ServerConfigResultEntity;
import com.onetech.mobilereader.https.RestClient;
import com.onetech.mobilereader.interfaces.CommonIF;
import com.onetech.mobilereader.entityresult.CategoryResultEntity;
import com.onetech.mobilereader.entityresult.UserResultEntity;
import com.onetech.mobilereader.uitls.CommonUtils;
import com.onetech.mobilereader.uitls.StoreUtils;
import com.onetech.otcore.db.store.SimpleStoreIF;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class CommonModel extends BaseModel implements CommonIF {

	private static CommonIF _instance;
	private static final Lock createLock = new ReentrantLock();

	private static final int STORE_EXPIRE = 3 * 24 * 60 * 60; // 3 day

	public static CommonIF getInstance() {
		if (_instance == null) {
			createLock.lock();
			_instance = new CommonModel();
			createLock.unlock();
		}
		return _instance;
	}

	private CommonModel() {

	}

	@Override
	SimpleStoreIF getStoreAdapter() {
		return StoreUtils.getStoreAdapter(Cache.COMMON_TABLE,
				OTApplicationContext.getContext(), Cache.COMMON_TABLE_SIZE);
	}

	@Override
	void setStore(String key, String value) {
		this.getStoreAdapter().put(key, value, STORE_EXPIRE);
	}

	@Override
	void setStore(String key, String value, int expiredTime) {
		this.getStoreAdapter().put(key, value, expiredTime);
	}
	// Category store utils
	@Override
	public void getCategory(final Callback<CategoryResultEntity> callback) {
		RestClient.get().getCategory(new Callback<CategoryResultEntity>() {
			@Override
			public void success(CategoryResultEntity categoryResultEntity, Response response) {
				callback.success(categoryResultEntity, response);
			}

			@Override
			public void failure(RetrofitError error) {
				callback.failure(error);
			}
		});
	}
	@Override
	public void saveUserEntity(UserEntity user) {
		if (user == null) {
			return;
		}
		Gson gs = new Gson();
		String data = gs.toJson(user);
		getStoreAdapter().put(DBConfig.CacheKey.USER_INFO_KEY, data);
	}
	@Override
	public void getServerConfig(RequestEntity request, final Callback<ServerConfigResultEntity> callback) {
		if(request == null || callback == null) {
			return;
		}
		RestClient.get().getServerConfig(request.registerId, request.email, request.signKey, new Callback<ServerConfigResultEntity>() {
			@Override
			public void success(ServerConfigResultEntity serverConfigResultEntity, Response response) {
				callback.success(serverConfigResultEntity, response);
			}

			@Override
			public void failure(RetrofitError error) {
				callback.failure(error);
			}
		});
	}
	// End user store utils

	@Override
	public void searchBookName(String bookName, final Callback<BookResultEntity> callback) {
		RestClient.get().searchBookName(bookName, new Callback<BookResultEntity>() {
			@Override
			public void success(BookResultEntity bookResultEntity, Response response) {
				callback.success(bookResultEntity, response);
			}

			@Override
			public void failure(RetrofitError error) {
				callback.failure(error);
			}
		});
	}
}
