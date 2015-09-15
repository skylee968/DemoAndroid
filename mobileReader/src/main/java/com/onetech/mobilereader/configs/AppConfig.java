package com.onetech.mobilereader.configs;

public class AppConfig {
	public static final String AUTH_KEY     = "S^GemqP=Y_8&bTVr?sD080990";

	public static class BUNDLE_KEY {
		public static final String EBOOK_FILE = "ebookfile";
		public static final String BOOK_DETAIL = "bookdetail";
		public static final String CATEGORY = "currentCategory";
	}
	public static final class CategoryType {
		public static final String HOT_BOOK		= "hotbooks";
		public static final String NEW_BOOK		= "newbooks";
		public static final String BEST_BOOK	= "bestbooks";
	}
	public static final class UrlRequest {
		public static final String domain = "";
		public static final String GET_ALL_CATEGORY = domain + "";
		public static final String GET_USER_INFO = domain + "";
		public static final String GET_LIST_BOOK = domain + "";
		public static final String GET_LIST_BOOK_DETAIL = domain + "";
	}
}
