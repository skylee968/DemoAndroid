package com.onetech.mobilereader.https;

import com.onetech.mobilereader.OTApplicationContext;
import com.onetech.mobilereader.uitls.CommonUtils;
import com.onetech.mobilereader.uitls.DeviceUtils;
import com.squareup.okhttp.OkHttpClient;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by thienlm on 7/5/2015.
 */
public class RestClient {
    private static RequestAPI REST_CLIENT;
    private static String ROOT                      = "http://52.74.55.122/api";
    public static String BOOK_IMAGE_COVER_URL       = ROOT + "/book/icon/";
    public static String BOOK_DOWNLOAD              = ROOT +"/download/";
    static {
        setupRestClient();
    }
    private RestClient(){}
    public static RequestAPI get() {
        return REST_CLIENT;
    }
    private static void setupRestClient() {
        RequestInterceptor mRequestInterceptor  = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("x-app-version", CommonUtils.getVersionName());
                request.addHeader("x-device-id", DeviceUtils.getDeviceId(OTApplicationContext.getContext()));
            }
        };
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(ROOT)
                .setRequestInterceptor(mRequestInterceptor)
                .setClient(new OkClient(new OkHttpClient()))
                .setLogLevel(RestAdapter.LogLevel.FULL);
        RestAdapter restAdapter     = builder.build();
        REST_CLIENT                 = restAdapter.create(RequestAPI.class);
    }
}
