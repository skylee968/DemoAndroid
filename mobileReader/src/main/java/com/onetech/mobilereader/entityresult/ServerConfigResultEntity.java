package com.onetech.mobilereader.entityresult;

import com.google.gson.annotations.SerializedName;
import com.onetech.mobilereader.entity.ServerConfigEntity;
import com.onetech.mobilereader.entityresult.ResultEntityBase;

/**
 * Created by thienlm on 7/7/2015.
 */
public class ServerConfigResultEntity extends ResultEntityBase {
    @SerializedName("data")
    public ServerConfigEntity data;
}
