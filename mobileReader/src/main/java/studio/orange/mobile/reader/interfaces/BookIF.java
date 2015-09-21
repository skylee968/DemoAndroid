package studio.orange.mobile.reader.interfaces;

import studio.orange.mobile.reader.entity.RequestEntity;
import studio.orange.mobile.reader.entityresult.BookResultEntity;

import retrofit.Callback;


public interface BookIF{
	public void getBooks(RequestEntity request, Callback<BookResultEntity> callback);
	public void getBookEntityById(RequestEntity request, Callback<BookResultEntity> callback);
}
