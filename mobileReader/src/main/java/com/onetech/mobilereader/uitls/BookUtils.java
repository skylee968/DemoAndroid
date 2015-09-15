package com.onetech.mobilereader.uitls;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.onetech.mobilereader.entity.BookEntity;
import com.onetech.mobilereader.entity.CategoryEntity;

import java.lang.reflect.Type;

/**
 * Created by thienlm on 8/14/2015.
 */
public final class BookUtils {
    public static String getExtensionFile(String url) {
        return ".epub";
//        if(url == null || url.trim().length() < 1) {
//            return ".epub";
//        }
//        return url.substring(url.lastIndexOf("."), url.length());
    }
    public static boolean isDowloadedBook(String url) {
        return true;
    }
    public static BookEntity deserializeBookFromJson(String json){
        BookEntity result = null;
        if(json == null || json.equals("")){
            return result;
        }
        try {
            result 	= new BookEntity();
            Gson gs = new Gson();
            Type listType = new TypeToken<BookEntity>(){}.getType();
            result = (BookEntity) gs.fromJson(json, listType);
        } catch (JsonSyntaxException e) {
            return null;
        }
        return result;
    }
    public static String convertObjectToJson(Object object) {
        Gson gson = new Gson();
        return  gson.toJson(object);
    }
    public static CategoryEntity deserializeCategoryFromJson(String json){
        CategoryEntity result = null;
        if(json == null || json.equals("")){
            return result;
        }
        try {
            result 	= new CategoryEntity();
            Gson gs = new Gson();
            Type listType = new TypeToken<CategoryEntity>(){}.getType();
            result = (CategoryEntity) gs.fromJson(json, listType);
        } catch (JsonSyntaxException e) {
            return null;
        }
        return result;
    }
}
