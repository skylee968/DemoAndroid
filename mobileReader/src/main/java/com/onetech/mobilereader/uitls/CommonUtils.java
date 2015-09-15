package com.onetech.mobilereader.uitls;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Base64;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.onetech.mobilereader.OTApplicationContext;
import com.onetech.mobilereader.configs.AppConfig;
import com.onetech.mobilereader.entity.BookEntity;
import com.onetech.mobilereader.entity.RequestEntity;
import com.onetech.mobilereader.entity.UserEntity;

public final class CommonUtils {
	private CommonUtils() {

	}
	public static void showToast(String message) {
		Toast.makeText(OTApplicationContext.getContext(), message, Toast.LENGTH_LONG).show();
	}
	public static float dpToPx(Context context, float dp) {
		if (context == null) {
			return -1;
		}
		return dp * context.getResources().getDisplayMetrics().density;
	}

	public static float pxToDp(Context context, float px) {
		if (context == null) {
			return -1;
		}
		return px / context.getResources().getDisplayMetrics().density;
	}
	public static String getVersionName() {
		try {
			return OTApplicationContext.getContext().getPackageManager().getPackageInfo(OTApplicationContext.getContext().getPackageName(), 0).versionName;
		} catch (Exception e) {
			return null;
		}
	}
	public static String generateStoreKey(RequestEntity request) {
		if(request == null) {
			return null;
		}
		return request.type + request.id;
	}
	@SuppressWarnings("unchecked")
	public static List<Long> deserializeListLongFromJson(String json){
		List<Long> result = null;
		if(json == null || json.equals("")){
			return result;
		}
		try {
			result 	= new ArrayList<Long>();
			Gson gs = new Gson();
			Type listType = new TypeToken<List<Integer>>(){}.getType();
			result = (List<Long>) gs.fromJson(json, listType);
		} catch (JsonSyntaxException e) {
			return null;
		}
		return result;
	}
	@SuppressWarnings("unchecked")
	public static List<Integer> deserializeListIntegerFromJson(String json){
		List<Integer> result = null;
		if(json == null || json.equals("")){
			return result;
		}
		try {
			result 	= new ArrayList<Integer>();
			Gson gs = new Gson();
			Type listType = new TypeToken<List<Integer>>(){}.getType();
			result = (List<Integer>) gs.fromJson(json, listType);
		} catch (JsonSyntaxException e) {
			return null;
		}
		return result;
	}
	@SuppressWarnings("unchecked")
	public static List<String> deserializeListStringFromJson(String json){
		List<String> result = null;
		if(json == null || json.equals("")){
			return result;
		}
		try {
			result 	= new ArrayList<String>();
			Gson gs = new Gson();
			Type listType = new TypeToken<List<String>>(){}.getType();
			result = (List<String>) gs.fromJson(json, listType);
		} catch (JsonSyntaxException e) {
			return null;
		}
		return result;
	}
	public static void openWebUrl(String url) {
		Intent launchIntent = null;
		try {
			launchIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
			launchIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			OTApplicationContext.getContext().startActivity(launchIntent);
		} catch (ActivityNotFoundException ex) {
			return;
		}
	}
	public static String generateSignature(String key){
		if(key == null || key.trim().length() < 1) {
			return null;
		}
		String result = null;
		String base64Code = null;
		try {
			base64Code = Base64.encodeToString(key.getBytes("UTF-8"), Base64.DEFAULT);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		base64Code = base64Code.replaceAll("(?:\\r\\n|\\n\\r|\\n|\\r)", "");
		String str = base64Code + AppConfig.AUTH_KEY;
		result = md5(str);
		return result;
	}
	public static final String md5(final String s) {
		try {
			// Create MD5 Hash
			MessageDigest digest = java.security.MessageDigest
					.getInstance("MD5");
			digest.update(s.getBytes());
			byte messageDigest[] = digest.digest();

			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < messageDigest.length; i++) {
				String h = Integer.toHexString(0xFF & messageDigest[i]);
				while (h.length() < 2)
					h = "0" + h;
				hexString.append(h);
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static UserEntity deserializeStringToUserEntity(String json) {
		UserEntity user = null;
		try {
			Gson gs 	= new Gson();
			Type type 	= new TypeToken<UserEntity>(){}.getType();
			user 		= (UserEntity) gs.fromJson(json, type);
		} catch(JsonSyntaxException ex) {
			ex.printStackTrace();
		}
		return user;
	}
}
