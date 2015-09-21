package studio.orange.mobile.reader.uitls;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import studio.orange.mobile.reader.configs.Constants;
import studio.orange.mobile.reader.entity.BookEntity;
import studio.orange.mobile.reader.entity.BookLikedEntity;
import studio.orange.mobile.reader.entity.UserEntity;
import studio.orange.mobile.reader.models.UserModel;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thienlm on 8/23/2015.
 */
public class UserUtils {

    public static boolean isUserValid() {
        UserEntity user = getUserEntity();
        if(user != null && user.getId() != null && user.getId().trim().length() >0) {
            return true;
        }
        return false;
    }
    public static UserEntity getUserEntity() {
        if(Constants.mUserInfo == null) {
            Constants.mUserInfo = UserModel.getInstance().getUserAsync();
        }
        if(Constants.mUserInfo != null) {
            loadUserBookLiked();
        }
        return Constants.mUserInfo;
    }
    public static void loadUserBookLiked() {
        if(Constants.mUserBookLiked == null) {
            Constants.mUserBookLiked = UserModel.getInstance().getBookLikedFromStore();
        }
    }
    public static boolean checkUserBookLiked(BookEntity book) {
        if(book == null) {
            return false;
        }
        if(Constants.mUserBookLiked == null) {
            Constants.mUserBookLiked = UserModel.getInstance().getBookLikedFromStore();
        }
        if(Constants.mUserBookLiked != null) {
            int size = Constants.mUserBookLiked.size();
            for(int i = 0; i < size; i++) {
                if(book.getId().equals(Constants.mUserBookLiked.get(i).bookId)) {
                    return true;
                }
            }
        }
        return false;
    }
    public static List<BookLikedEntity> deserializeBookLikedFromJson(String json){
        List<BookLikedEntity> result = null;
        if(json == null || json.equals("")){
            return result;
        }
        try {
            result 	= new ArrayList<BookLikedEntity>();
            Gson gs = new Gson();
            Type listType = new TypeToken<List<BookLikedEntity>>(){}.getType();
            result = (List<BookLikedEntity>) gs.fromJson(json, listType);
        } catch (JsonSyntaxException e) {
            return null;
        }
        return result;
    }
}
