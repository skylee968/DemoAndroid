package studio.orange.mobile.reader.interfaces;

import java.util.List;

import studio.orange.mobile.reader.entity.BookEntity;

public interface UserBookDetailIF {
	public List<BookEntity> getListBookDetail(String requestUrl, List<Long> ids);
	public BookEntity getBookDetail(String requestUrl, long id);
}
