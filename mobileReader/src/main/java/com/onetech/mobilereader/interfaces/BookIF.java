package com.onetech.mobilereader.interfaces;

import com.onetech.mobilereader.entity.RequestEntity;
import com.onetech.mobilereader.entityresult.BookResultEntity;

import retrofit.Callback;


public interface BookIF{
	public void getBooks(RequestEntity request, Callback<BookResultEntity> callback);
	public void getBookEntityById(RequestEntity request, Callback<BookResultEntity> callback);
}
