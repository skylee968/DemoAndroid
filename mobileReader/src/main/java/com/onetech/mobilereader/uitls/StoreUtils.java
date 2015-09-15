package com.onetech.mobilereader.uitls;

import android.content.Context;
import android.os.Environment;

import com.onetech.mobilereader.configs.DBConfig;
import com.onetech.otcore.db.store.SQLiteStore;
import com.onetech.otcore.db.store.SimpleStoreIF;

import java.io.File;

public final class StoreUtils {

	private StoreUtils(){
		
	}
	public static SimpleStoreIF getStoreAdapter(String name, Context context, int items){
		return SQLiteStore.getInstance(name, context, DBConfig.DBVERSION, items);
	}
	public static void createDirectory(Context _context, String _uri,
									   boolean isOverride) {
		File directory = new File(_uri);
		if (isOverride) {
			if (directory.exists()) {
				directory.delete();
			}
		} else {
			if (directory.exists()) {
				return;
			} else {
				directory.mkdirs();
			}
		}
	}
	public static String getBookDirectory() {
		return Environment.getExternalStorageDirectory().toString() + "/MobileReader/";
	}
	public static boolean isExistedFile(String fileName) {
		if(fileName == null && fileName.trim().length() < 1) {
			return false;
		}
		File file = new File(fileName);
		if (file.exists() && file.isFile()) {
			return true;
		}
		return false;
	}
}
