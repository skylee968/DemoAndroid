package studio.orange.mobile.reader.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by thienlm on 8/14/2015.
 */
public class MetaBookEntity {
    @SerializedName("publish_date")
    private String publishDate;

    @SerializedName("publish_by")
    private String publishBy;

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getPublishBy() {
        return publishBy;
    }

    public void setPublishBy(String publishBy) {
        this.publishBy = publishBy;
    }
}
