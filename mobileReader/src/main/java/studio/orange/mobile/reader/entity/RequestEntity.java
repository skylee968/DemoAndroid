package studio.orange.mobile.reader.entity;

import studio.orange.mobile.reader.OTApplicationContext;
import studio.orange.mobile.reader.configs.Constants;
import studio.orange.mobile.reader.uitls.DeviceUtils;

public class RequestEntity extends BaseEntity {
	public String id;
	public String cateId;
	public String type;
	public String url;
	public int pageIndex;
	public String pageNum;
	public boolean isRefresh;
	public String userId;

	public String username;
	public String email;
	public String registerId;
	public String signKey;
	public String passwords;

	public String imeiNumber;

	public REQUEST_TYPE requestType;
	public RequestEntity() {
		id 			= "";
		isRefresh 	= false;
		type 		= "";
		pageIndex	= 1;
		pageNum		= String.valueOf(Constants.PAGE_NUM);
		requestType	= REQUEST_TYPE.BOOKS;
		imeiNumber	= DeviceUtils.getDeviceId(OTApplicationContext.getContext());
	}
	public enum REQUEST_TYPE {
		BOOKS,
		SUGGEST_BOOKS,
		NEWS_BOOKS,
		SUGGEST_BOOK_CATEGORY,
		ALL_BOOK_CATEGORY,
		RELATIVE_BOOK,
		USER_FAVORITE_BOOK,
		USER_BOOK,
		SEARCH
	}
}
