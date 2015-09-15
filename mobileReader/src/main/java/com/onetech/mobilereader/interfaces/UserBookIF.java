package com.onetech.mobilereader.interfaces;

import java.util.List;

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
import com.onetech.otcore.http.corelib.Request;

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
