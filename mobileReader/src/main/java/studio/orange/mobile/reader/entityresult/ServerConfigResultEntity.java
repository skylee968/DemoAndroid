package studio.orange.mobile.reader.entityresult;

import com.google.gson.annotations.SerializedName;
import studio.orange.mobile.reader.entity.ServerConfigEntity;

/**
 * Created by thienlm on 7/7/2015.
 */
public class ServerConfigResultEntity extends ResultEntityBase {
    @SerializedName("data")
    public ServerConfigEntity data;
}
