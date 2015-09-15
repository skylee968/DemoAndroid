package com.onetech.mobilereader.models;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.onetech.mobilereader.OTApplicationContext;
import com.onetech.mobilereader.configs.DBConfig.Cache;
import com.onetech.mobilereader.entity.BookEntity;
import com.onetech.mobilereader.interfaces.BookDetailIF;
import com.onetech.mobilereader.uitls.StoreUtils;
import com.onetech.otcore.db.store.SimpleStoreIF;

public class BookDetailModel extends BaseModel implements BookDetailIF {
	private static BookDetailIF _instance;
	private static final Lock createLock = new ReentrantLock();
	private static final int STORE_EXPIRE = 3 * 24 * 60 * 60; // 3days
	
	public static BookDetailIF getInstance(){
		if(_instance == null){
			createLock.lock();
			_instance = new BookDetailModel();
			createLock.unlock();
		}
		return _instance;
	}
	private BookDetailModel(){
		
	}
	@Override
	SimpleStoreIF getStoreAdapter() {
		return StoreUtils.getStoreAdapter(Cache.BOOK_DETAIL_TABLE,
				OTApplicationContext.getContext(),
				Cache.BOOK_DETAIL_TABLE_SIZE);
	}

	@Override
	void setStore(String key, String value) {
		this.getStoreAdapter().put(key, value, STORE_EXPIRE);
	}

	@Override
	void setStore(String key, String value, int expiredTime) {
		this.getStoreAdapter().put(key, value, expiredTime);
	}

	@Override
	public List<BookEntity> getListBookDetail(String requestUrl, List<Long> ids) {
		if (ids == null || ids.isEmpty()) {
			return null;
		}
		List<BookEntity> result = new ArrayList<BookEntity>();
		List<String> keys = new ArrayList<String>(ids.size());
		List<Long> ids_miss = new ArrayList<Long>();
		for (Long myId : ids) {
			keys.add(String.valueOf(myId));
		}
		Map<String, String> data = this.getStoreAdapter().getMultiKeys(keys,
				null);
		if (data != null && data.size() > 0) {
			Iterator<String> iter = keys.iterator();
			while (iter.hasNext()) {
				String key = iter.next();
				String val = data.get(key);

				BookEntity book = convertJsonStringToBookDetail(val);
				if (book != null) {
					result.add(book);
				} else {
					try {
						ids_miss.add(Long.valueOf(key));
					} catch (NumberFormatException e) {
					}
				}
			}
		} else {
			ids_miss = ids;
		}
		if(ids_miss!=null && ids_miss.size()>0){
			List<BookEntity> listBook=getListBookDetailFromServer(requestUrl, ids_miss);
			if(listBook!=null && listBook.size()>0){
				result.clear();
				result.addAll(listBook);
			}
		}else{
			return result;
		}
		return null;
	}

	@Override
	public BookEntity getBookDetail(String requestUrl, long id) {
		BookEntity result = null;
		String resultString = this.getStoreAdapter().get(String.valueOf(id));
		if(resultString != null && resultString.length()>0){
			result = convertJsonStringToBookDetail(resultString);
		}
		if(result == null){
			result = getBookDetailFromServer(requestUrl, id);
		}
		return result;
	}
	private BookEntity getBookDetailFromServer(String requestUrl, long id){
		BookEntity result = null;
		return result;
	}
	private List<BookEntity> getListBookDetailFromServer(String requestUrl,List<Long> ids){
		List<BookEntity> result = null;

		return result;
	}
	private BookEntity convertJsonStringToBookDetail(String json) {
		if (json == null || json.equals("")) {
			return null;
		}
		BookEntity result = new BookEntity();
		Gson gson = new Gson();
		Type typeClass = new TypeToken<BookEntity>() {
		}.getType();
		result = (BookEntity) gson.fromJson(json, typeClass);
		return result;
	}
	private void storeBookDetail(BookEntity book){
		Gson gson = new Gson();
		String data = gson.toJson(book);
		this.getStoreAdapter().put(String.valueOf(book.getId()), data);
	}
}
