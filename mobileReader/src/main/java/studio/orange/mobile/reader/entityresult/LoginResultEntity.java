package studio.orange.mobile.reader.entityresult;

import com.google.gson.annotations.SerializedName;
import studio.orange.mobile.reader.entity.UserEntity;

/**
 * Created by thienlm on 7/20/2015.
 */
public class LoginResultEntity extends ResultEntityBase {
    @SerializedName("data")
    public UserEntity data;
}
