package studio.orange.mobile.reader.interfaces;

import java.util.List;

import studio.orange.mobile.reader.entity.BookLikedEntity;
import studio.orange.mobile.reader.entity.RequestEntity;
import studio.orange.mobile.reader.entity.UserEntity;
import studio.orange.mobile.reader.entityresult.BookLikedResultEntity;
import studio.orange.mobile.reader.entityresult.BookResultEntity;
import studio.orange.mobile.reader.entityresult.ListBookLikedResultEntity;
import studio.orange.mobile.reader.entityresult.LoginResultEntity;
import studio.orange.mobile.reader.entityresult.RegisterResultEntity;
import studio.orange.mobile.reader.entityresult.UserResultEntity;

import retrofit.Callback;

public interface UserBookIF {
	void getUserInfo(RequestEntity request, Callback<UserResultEntity> callback);
	UserEntity getUserAsync();

	void loginUser(RequestEntity request, Callback<LoginResultEntity> callback);
	void registerUser(RequestEntity request, Callback<RegisterResultEntity> callback);
	void getCoinUser(RequestEntity request, Callback<UserResultEntity> callback);

	List<BookLikedEntity> getBookLikedFromStore();
	void getBookLiked(RequestEntity request, Callback<ListBookLikedResultEntity> callback);
	void setBookLiked(RequestEntity request, Callback<BookLikedResultEntity> callback);
	void removeBookLiked(RequestEntity request, Callback<BookLikedResultEntity> callback);
	void logout();

	void getBook(RequestEntity request, Callback<BookResultEntity> callback);
	void getBookById(RequestEntity request, Callback<BookResultEntity> callback);
	void addBook(RequestEntity request, Callback<BookResultEntity> callback);


}
