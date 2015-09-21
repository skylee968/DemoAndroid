package studio.orange.mobile.reader.interfaces;

import studio.orange.mobile.reader.entity.RequestEntity;
import studio.orange.mobile.reader.entity.UserEntity;
import studio.orange.mobile.reader.entityresult.BookResultEntity;
import studio.orange.mobile.reader.entityresult.CategoryResultEntity;
import studio.orange.mobile.reader.entityresult.ServerConfigResultEntity;

import retrofit.Callback;
import studio.orange.mobile.reader.socials.SocialProfile;

public interface CommonIF {

	// Category store utils
	void getCategory(Callback<CategoryResultEntity> callback);

	void getServerConfig(RequestEntity request, Callback<ServerConfigResultEntity> callback);
//	void saveUserEntity(UserEntity user);
	void searchBookName(String bookName, Callback<BookResultEntity> callback);
}
