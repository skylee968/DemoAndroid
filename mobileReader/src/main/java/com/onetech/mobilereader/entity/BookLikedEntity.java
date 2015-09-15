package com.onetech.mobilereader.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by thienlm on 8/23/2015.
 */
public class BookLikedEntity extends BaseEntity {
    @SerializedName("_id")
    public String id;

    @SerializedName("like_date")
    public String likeDate;

    @SerializedName("book_id")
    public String bookId;

    @SerializedName("user_id")
    public String userId;
}
