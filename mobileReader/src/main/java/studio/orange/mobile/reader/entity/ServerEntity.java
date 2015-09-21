package studio.orange.mobile.reader.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by thienlm on 7/8/2015.
 */
public class ServerEntity extends BaseEntity {
    @SerializedName("is_maintenance")
    public boolean is_maintenance;

    @SerializedName("is_new_version")
    public boolean is_new_version;

    @SerializedName("target_version_update")
    public String target_version_update;

    @SerializedName("is_force_update")
    public boolean is_force_update;

    @SerializedName("url_update")
    public String url_update;
}
