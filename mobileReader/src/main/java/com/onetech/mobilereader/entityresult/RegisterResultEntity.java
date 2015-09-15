package com.onetech.mobilereader.entityresult;

import com.google.gson.annotations.SerializedName;
import com.onetech.mobilereader.entity.UserEntity;

/**
 * Created by thienlm on 7/20/2015.
 */
public class RegisterResultEntity extends ResultEntityBase {

    @SerializedName("data")
    public UserEntity data;
}
