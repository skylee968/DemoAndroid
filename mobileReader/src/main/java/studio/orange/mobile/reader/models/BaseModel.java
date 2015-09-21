package studio.orange.mobile.reader.models;

import com.onetech.otcore.db.store.SimpleStoreIF;

public abstract class BaseModel{
	abstract SimpleStoreIF getStoreAdapter();
	abstract void setStore(String key, String value);
	abstract void setStore(String key, String value, int expiredTime);
}
