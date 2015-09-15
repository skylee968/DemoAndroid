package com.onetech.otcore.http.coreapi;

import org.apache.http.HttpResponse;

import com.onetech.otcore.http.corelib.HttpErrors;

public interface IDelegateAPIRequestGetter {
	public void onGetAPIRequestDataCompleted(int requestType,
			APIRequestResult data);

	public void onGetStringDataCompleted(int requestType, String data);

	public void onGetBinaryDataCompleted(int requestType, byte[] data);

	public void onGetRawDataCompleted(int requestType, HttpResponse data);

	public void onGetDataError(int requestType, HttpErrors errorCode);
}
