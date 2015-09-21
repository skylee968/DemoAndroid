package studio.orange.mobile.reader.models;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import com.google.gson.Gson;

import studio.orange.mobile.reader.OTApplicationContext;
import studio.orange.mobile.reader.configs.DBConfig;
import studio.orange.mobile.reader.configs.DBConfig.Cache;
import studio.orange.mobile.reader.entity.RequestEntity;
import studio.orange.mobile.reader.entity.UserEntity;
import studio.orange.mobile.reader.entityresult.BookResultEntity;
import studio.orange.mobile.reader.entityresult.ServerConfigResultEntity;
import studio.orange.mobile.reader.https.RestClient;
import studio.orange.mobile.reader.interfaces.CommonIF;
import studio.orange.mobile.reader.entityresult.CategoryResultEntity;
import studio.orange.mobile.reader.uitls.StoreUtils;
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
//	@Override
//	public void saveUserEntity(UserEntity user) {
//		if (user == null) {
//			return;
//		}
//		Gson gs = new Gson();
//		String data = gs.toJson(user);
//		getStoreAdapter().put(DBConfig.CacheKey.USER_INFO_KEY, data);
//	}
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
