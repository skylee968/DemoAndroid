package studio.orange.mobile.reader.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by thienlm on 7/7/2015.
 */
public class ServerConfigEntity extends BaseEntity{
    @SerializedName("system")
    public ServerEntity system;

    @SerializedName("user")
    public UserEntity user;
}
