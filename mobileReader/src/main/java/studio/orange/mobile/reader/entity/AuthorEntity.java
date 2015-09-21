package studio.orange.mobile.reader.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by thienlm on 8/14/2015.
 */
public class AuthorEntity extends BaseEntity {
    @SerializedName("first_name")
    private String firstName;

    @SerializedName("last_name")
    private String lastName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
