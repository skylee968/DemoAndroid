package com.onetech.mobilereader.https;

import com.onetech.mobilereader.entityresult.BookLikedResultEntity;
import com.onetech.mobilereader.entityresult.BookResultEntity;
import com.onetech.mobilereader.entityresult.CategoryResultEntity;
import com.onetech.mobilereader.entityresult.ListBookLikedResultEntity;
import com.onetech.mobilereader.entityresult.LoginResultEntity;
import com.onetech.mobilereader.entityresult.RegisterResultEntity;
import com.onetech.mobilereader.entityresult.ServerConfigResultEntity;
import com.onetech.mobilereader.entityresult.UserResultEntity;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Part;
import retrofit.http.Path;
import retrofit.http.Query;

;


/**
 * Created by thienlm on 7/5/2015.
 */
public interface RequestAPI {
    @GET("/")
    void getBooks(@Query("id") String id, @Query("cateId") String cateId, @Query("page") String page, Callback<BookResultEntity> callback);

    @GET("/suggestbook")
    void getSuggestBooks(@Query("id") String id, @Query("cateId") String cateId, @Query("page") String page, Callback<BookResultEntity> callback);

    @GET("/suggestbook")
    void getRelativeBooks(@Query("id") String id, @Query("cateId") String cateId, @Query("page") String page, Callback<BookResultEntity> callback);

    @GET("/newbook")
    void getNewBooks(@Query("id") String id, @Query("cateId") String cateId, @Query("page") String page, Callback<BookResultEntity> callback);

    @GET("/bookbycategory/{cateId}")
    void getAllBookCategory(@Path("cateId") String cateId, @Query("page") String page, Callback<BookResultEntity> callback);

    @GET("/bookbycategory/{cateId}")
    void getSuggestBookCategory(@Path("cateId") String cateId, @Query("page") String page, Callback<BookResultEntity> callback);

    @GET("/category")
    void getCategory(Callback<CategoryResultEntity> callback);

    @GET("/")
    void getUserInfo(@Query("id") String id, Callback<UserResultEntity> callback);

    @GET("/like/books/{userId}")
    void getUserBookLiked(@Path("userId") String userId, Callback<BookResultEntity> callback);


    @Multipart
    @PUT("/adduser")
    void getServerConfig(@Field("gcm_regid") String registerId, @Field("email") String email, @Field("sign") String sign, Callback<ServerConfigResultEntity> callback);

    @FormUrlEncoded
    @PUT("/register")
    void registerUser(@Field("name") String username, @Field("email") String email, @Field("password") String password, @Field("imei") String imei, @Field("gcm_id") String registerId, Callback<RegisterResultEntity> callback);

    @FormUrlEncoded
    @POST("/login")
    void loginUser(@Field("name") String username, @Field("password") String password, @Field("sign") String sign, Callback<LoginResultEntity> callback);

    @GET("/like/{useId}")
    void getBookLiked(@Path("useId") String useId, Callback<ListBookLikedResultEntity> callback);

    @FormUrlEncoded
    @PUT("/like")
    void setBookLiked(@Field("user_id") String userId, @Field("book_id") String bookId, Callback<BookLikedResultEntity> callback);

    @GET("/book/search/name/{bookName}")
    void searchBookName(@Path("bookName") String bookName, Callback<BookResultEntity> callback);
}
