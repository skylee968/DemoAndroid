package com.onetech.mobilereader.models;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.json.JSONArray;

import com.google.gson.Gson;
import com.onetech.mobilereader.OTApplicationContext;
import com.onetech.mobilereader.configs.Constants;
import com.onetech.mobilereader.configs.DBConfig;
import com.onetech.mobilereader.configs.DBConfig.Cache;
import com.onetech.mobilereader.entity.BookEntity;
import com.onetech.mobilereader.entity.BookLikedEntity;
import com.onetech.mobilereader.entity.RequestEntity;
import com.onetech.mobilereader.entity.UserEntity;
import com.onetech.mobilereader.entityresult.BookLikedResultEntity;
import com.onetech.mobilereader.entityresult.BookResultEntity;
import com.onetech.mobilereader.entityresult.ListBookLikedResultEntity;
import com.onetech.mobilereader.entityresult.LoginResultEntity;
import com.onetech.mobilereader.entityresult.RegisterResultEntity;
import com.onetech.mobilereader.entityresult.UserResultEntity;
import com.onetech.mobilereader.entityresult.UserResultEntity;
import com.onetech.mobilereader.https.RestClient;
import com.onetech.mobilereader.interfaces.UserBookIF;
import com.onetech.mobilereader.uitls.CommonUtils;
import com.onetech.mobilereader.uitls.StoreUtils;
import com.onetech.mobilereader.uitls.UserUtils;
import com.onetech.otcore.db.store.SimpleStoreIF;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class UserModel extends BaseModel implements UserBookIF {
//	private LruCache<String, List<Integer>> cache;
	
	private static UserBookIF _instance;
	private static final Lock createLock 			= new ReentrantLock();
	
	private static final int STORE_EXPIRE 			= 3*24*60*60; //3 day
	private static final int STORE_EXPIRE_5_DAYS 	= 5*24*60*60; //5 day

	
	public static UserBookIF getInstance(){
		if(_instance==null){
			createLock.lock();
			_instance = new UserModel();
			createLock.unlock();
		}
		return _instance;
	}
	private UserModel(){
		
	}
	@Override
	SimpleStoreIF getStoreAdapter() {
		return StoreUtils.getStoreAdapter(Cache.USER_TABLE, OTApplicationContext.getContext(), Cache.USER_TABLE_SIZE);
	}

	@Override
	void setStore(String key, String value) {
		this.getStoreAdapter().put(key, value, STORE_EXPIRE);
	}

	@Override
	void setStore(String key, String value, int expiredTime) {
		this.getStoreAdapter().put(key, value, expiredTime);
	}
//	private LruCache<String, List<Integer>> getCacheAdapter(String name, int size){
//		return (LruCache<String , List<Integer>>) LRUCacheMa
//	}


	public List<Integer> getUserBookLikedIdsAsync() {
		List<Integer> likedIds = null;
		likedIds = getLikedIdsFromStore("userid");
		if(likedIds != null && likedIds.size() > 0){
			return likedIds;
		}
		//likedIds = getUserBookLikedIdsFromAPI();
		return likedIds;
	}
	private List<Integer> getLikedIdsFromStore(String key){
		List<Integer> result = null;
		String json = getStoreAdapter().get(key);
		if(json != null && !json.equals("")){
			result = CommonUtils.deserializeListIntegerFromJson(json);
		}
		return result;
	}
	

	public void updateUserLiked(List<Integer> list) {
		if(list != null && list.size() > 0){
			Gson gs = new Gson();
			String temp = gs.toJson(list);
			getStoreAdapter().put("", temp);
		}
		
	}

	@Override
	public void getBook(RequestEntity request, Callback<BookResultEntity> callback) {

	}

	@Override
	public void getBookById(RequestEntity request, Callback<BookResultEntity> callback) {

	}

	@Override
	public void addBook(RequestEntity request, Callback<BookResultEntity> callback) {

	}

	@Override
	public void getUserInfo(RequestEntity request, final Callback<UserResultEntity> callback) {
		RestClient.get().getUserInfo(String.valueOf(request.userId), new Callback<UserResultEntity>() {
			@Override
			public void success(UserResultEntity userResultEntity, Response response) {
				callback.success(userResultEntity, response);
			}

			@Override
			public void failure(RetrofitError error) {
				callback.failure(error);
			}
		});
	}

	@Override
	public UserEntity getUserAsync() {
		String json = getStoreAdapter().get(DBConfig.CacheKey.USER_INFO_KEY);
		if(json != null) {
			Log.e("USER INFO:", json);
		}
		if(json != null && json.trim().length() > 1) {
			return CommonUtils.deserializeStringToUserEntity(json);
		}
		return null;
	}

	@Override
	public void loginUser(RequestEntity request, final Callback<LoginResultEntity> callback) {
		RestClient.get().loginUser(request.username, request.passwords, request.signKey, new Callback<LoginResultEntity>() {
			@Override
			public void success(LoginResultEntity loginResultEntity, Response response) {
				if (loginResultEntity != null && loginResultEntity.data != null) {
					saveUserInfoIntoStore(loginResultEntity.data);
					Constants.mUserInfo = loginResultEntity.data;

					// Get user liked
					RequestEntity request = new RequestEntity();
					request.userId = Constants.mUserInfo.getId();
					UserModel.getInstance().getBookLiked(request, null);
				}
				callback.success(loginResultEntity, response);
			}

			@Override
			public void failure(RetrofitError error) {
				callback.failure(error);
			}
		});
	}

	@Override
	public void registerUser(RequestEntity request, final Callback<RegisterResultEntity> callback) {
		RestClient.get().registerUser(request.username, request.email, request.passwords, request.imeiNumber, request.registerId, new Callback<RegisterResultEntity>() {
			@Override
			public void success(RegisterResultEntity registerResultEntity, Response response) {
				callback.success(registerResultEntity, response);
			}

			@Override
			public void failure(RetrofitError error) {
				callback.failure(error);
			}
		});
	}

	@Override
	public void getCoinUser(RequestEntity request, Callback<UserResultEntity> callback) {

	}

	@Override
	public List<BookLikedEntity> getBookLikedFromStore() {
		if(Constants.mUserInfo == null) {
			return null;
		}
		String temp = getStoreAdapter().get(DBConfig.CacheKey.USER_BOOK_LIKED + Constants.mUserInfo.getId());
		if(temp != null && temp.trim().length() > 0) {
			return UserUtils.deserializeBookLikedFromJson(temp);
		}
		return null;
	}
	@Override
	public void getBookLiked(final RequestEntity request, final Callback<ListBookLikedResultEntity> callback) {
		if (request.userId != null) {
			List<BookLikedEntity> result = getBookLikedFromStore();
			if(result != null) {
				ListBookLikedResultEntity temp 	= new ListBookLikedResultEntity();
				temp.data 						= result;
				callback.success(temp, null);
				return;
			}
		}
		RestClient.get().getBookLiked(request.userId, new Callback<ListBookLikedResultEntity>() {
			@Override
			public void success(ListBookLikedResultEntity result, Response response) {
				if (result != null && result.data != null && result.data.size() > 0 && request.userId != null) {
					Constants.mUserBookLiked = result.data;
					Gson gson = new Gson();
					String data = gson.toJson(result.data);
					setStore(DBConfig.CacheKey.USER_BOOK_LIKED + request.userId, data, STORE_EXPIRE_5_DAYS);
				}
				if (callback != null) {
					callback.success(result, response);
				}
			}

			@Override
			public void failure(RetrofitError error) {
				if (callback != null) {
					callback.failure(error);
				}
			}
		});
	}
	@Override
	public void setBookLiked(RequestEntity request, final Callback<BookLikedResultEntity> callback) {
		UserUtils.loadUserBookLiked();
		if(Constants.mUserBookLiked == null) {
			Constants.mUserBookLiked = new ArrayList<BookLikedEntity>();
		}

		BookLikedEntity liked = new BookLikedEntity();
		liked.bookId = request.id;
		liked.likeDate = String.valueOf(System.currentTimeMillis());
		Constants.mUserBookLiked.add(liked);

		RestClient.get().setBookLiked(request.userId, request.id, new Callback<BookLikedResultEntity>() {
			@Override
			public void success(BookLikedResultEntity bookLikedResultEntity, Response response) {
				callback.success(bookLikedResultEntity, response);
			}

			@Override
			public void failure(RetrofitError error) {
				callback.failure(error);
			}
		});
	}

	@Override
	public void removeBookLiked(RequestEntity request, Callback<BookLikedResultEntity> callback) {

	}

	@Override
	public void logout() {
		this.getStoreAdapter().remove(DBConfig.CacheKey.USER_INFO_KEY);
	}

	private void saveUserInfoIntoStore(UserEntity user) {
		if(user == null) {
			return;
		}
		Gson gson 		= new Gson();
		String result 	= gson.toJson(user);
		setStore(DBConfig.CacheKey.USER_INFO_KEY, result, -1);
	}
}
