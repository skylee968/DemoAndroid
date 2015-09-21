package studio.orange.mobile.reader.https;

import studio.orange.mobile.reader.entityresult.BookLikedResultEntity;
import studio.orange.mobile.reader.entityresult.BookResultEntity;
import studio.orange.mobile.reader.entityresult.CategoryResultEntity;
import studio.orange.mobile.reader.entityresult.ListBookLikedResultEntity;
import studio.orange.mobile.reader.entityresult.LoginResultEntity;
import studio.orange.mobile.reader.entityresult.RegisterResultEntity;
import studio.orange.mobile.reader.entityresult.ServerConfigResultEntity;
import studio.orange.mobile.reader.entityresult.UserResultEntity;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;

;


/**
 * Created by thienlm on 7/5/2015.
 */
public interface RequestAPI {
    @GET("/{page}/{pageNum}")
    void getBooks(@Query("id") String id, @Query("cateId") String cateId, @Path("page") String page, @Path("pageNum") String pageNum, Callback<BookResultEntity> callback);

    @GET("/suggestbook/{page}/{pageNum}")
    void getSuggestBooks(@Query("id") String id, @Query("cateId") String cateId, @Path("page") String page, @Path("pageNum") String pageNum, Callback<BookResultEntity> callback);

    @GET("/suggestbook/{page}/{pageNum}")
    void getRelativeBooks(@Query("id") String id, @Query("cateId") String cateId, @Path("page") String page, @Path("pageNum") String pageNum, Callback<BookResultEntity> callback);

    @GET("/newbook/{page}/{pageNum}")
    void getNewBooks(@Query("id") String id, @Query("cateId") String cateId, @Path("page") String page, @Path("pageNum") String pageNum, Callback<BookResultEntity> callback);

    @GET("/bookbycategory/{cateId}/{page}/{pageNum}")
    void getAllBookCategory(@Path("cateId") String cateId, @Path("page") String page, @Path("pageNum") String pageNum, Callback<BookResultEntity> callback);

    @GET("/bookbycategory/{cateId}/{page}/{pageNum}")
    void getSuggestBookCategory(@Path("cateId") String cateId, @Path("page") String page, @Path("pageNum") String pageNum, Callback<BookResultEntity> callback);

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
