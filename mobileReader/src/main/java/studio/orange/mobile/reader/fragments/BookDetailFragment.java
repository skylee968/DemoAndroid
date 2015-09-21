package studio.orange.mobile.reader.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dd.processbutton.iml.SubmitProcessButton;
import com.nostra13.universalimageloader.core.ImageLoader;
import studio.orange.mobile.reader.R;
import studio.orange.mobile.reader.activities.BookDetailActivity;
import studio.orange.mobile.reader.configs.Constants;
import studio.orange.mobile.reader.entity.BookEntity;
import studio.orange.mobile.reader.entity.RequestEntity;
import studio.orange.mobile.reader.entityresult.BookLikedResultEntity;
import studio.orange.mobile.reader.https.RestClient;
import studio.orange.mobile.reader.models.UserModel;
import studio.orange.mobile.reader.uitls.AvailableSpaceUtil;
import studio.orange.mobile.reader.uitls.BookUtils;
import studio.orange.mobile.reader.uitls.CommonUtils;
import studio.orange.mobile.reader.uitls.StoreUtils;
import studio.orange.mobile.reader.uitls.UserUtils;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by thienlm on 7/30/2015.
 */
public class BookDetailFragment extends BaseFragment implements View.OnClickListener {
    private BookDetailActivity mActivity;
    private ImageView mImgCover;
    private TextView mTxtTitle;
    private TextView mTxtAuthor;
    private TextView mTxtDescription;
    private SubmitProcessButton mBtnDownload;
    private ImageView mImgLike;
    private TextView mTxtTotalLike;

    private ImageView mBtnLike;
    private ImageView mBtnShare;

    private BookEntity mBook;

    private DownloadBookAsync mDownloadBookAsync = null;

    private boolean isExistedBook = false;
    private String mBookFile;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(mView == null) {
            mView = inflater.inflate(R.layout.fragment_book_detail, container, false);
            initView();
            initListener();
        } else if(mView.getParent() != null) {
                ((ViewGroup)mView.getParent()).removeView(mView);
            }
        return mView;
    }
    private void initView() {
        initImageLoader();
        mActivity       = (BookDetailActivity) getActivity();
        mImgCover       = (ImageView) mView.findViewById(R.id.img_cover_detail);
        mTxtTitle       = (TextView) mView.findViewById(R.id.title_book_detail);
        mTxtAuthor      = (TextView) mView.findViewById(R.id.txt_author_book_detail);
        mTxtDescription = (TextView) mView.findViewById(R.id.txt_book_description);
        mBtnDownload    = (SubmitProcessButton) mView.findViewById(R.id.btn_download);
        mImgLike        = (ImageView) mView.findViewById(R.id.btn_like);
        mTxtTotalLike   = (TextView) mView.findViewById(R.id.txt_total_like);

        mBtnLike        = (ImageView) mView.findViewById(R.id.img_favorite_btn);
        mBtnShare       = (ImageView) mView.findViewById(R.id.img_share_btn);

        mBook           = getCurrentBook();
        //mBook.setBookLink("http://download.truyen368.com/epub/huong-han-truyen368-com.epub");
        if(mBook != null) {
            mTxtTitle.setText(mBook.getName());
            mTxtAuthor.setText(mBook.getAuthor().getLastName());
            mTxtDescription.setText(mBook.getDescription());
            mTxtTotalLike.setText(" " + String.valueOf(mBook.getTotalLike()));
            ImageLoader.getInstance().displayImage(RestClient.BOOK_IMAGE_COVER_URL + mBook.getId(), mImgCover, mOptions);
        }
    }
    private void initListener() {
        mBtnDownload.setOnClickListener(this);
        mBtnLike.setOnClickListener(this);
        mBtnShare.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btn_download:
                if(isExistedBook) {
                    //Toast.makeText(getActivity(), "Existed file:" + mBookFile, Toast.LENGTH_LONG).show();
                    if(mBookFile != null) {
                        mActivity.startReadingActivity(mBookFile);
                    }
                } else {
                    mBtnDownload.setEnabled(false);
                    if(mBook.isFrees()) {
                        downloadBook();
                    } else {
                        CommonUtils.showToast(mActivity.getString(R.string.common_msg_please_login));
                    }
                }
                break;
            case R.id.img_favorite_btn:
                //CommonUtils.showToast("Liked");
                if(UserUtils.isUserValid()) {
                    RequestEntity request   = new RequestEntity();
                    request.userId          = Constants.mUserInfo.getId();
                    request.id              = mBook.getId();

                    UserModel.getInstance().setBookLiked(request, new Callback<BookLikedResultEntity>() {
                        @Override
                        public void success(BookLikedResultEntity bookLikedResultEntity, Response response) {
                            if(bookLikedResultEntity != null && bookLikedResultEntity.data != null && bookLikedResultEntity.data.id != null) {
                                //CommonUtils.showToast(bookLikedResultEntity.data.userId);
                                mTxtTotalLike.setText(" " + String.valueOf(mBook.getTotalLike() + 1));
                                mBtnLike.setImageResource(R.drawable.ic_favorite_active);
                                mBtnLike.setEnabled(false);
                            }
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            CommonUtils.showToast(error.getMessage());
                        }
                    });
                } else {
                    CommonUtils.showToast(mActivity.getString(R.string.common_msg_please_login));
                }
                break;
            case R.id.img_share_btn:
                CommonUtils.showToast("Shared");
                break;
            default:
                break;
        }
    }

    private void downloadBook() {
        if(mDownloadBookAsync == null || mDownloadBookAsync.getStatus() == AsyncTask.Status.FINISHED) {
            mDownloadBookAsync = new DownloadBookAsync(mBook);
            mDownloadBookAsync.execute();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        checkExistedBook();
    }
    private void checkExistedBook() {
        mBookFile       = StoreUtils.getBookDirectory() + mBook.getId() + BookUtils.getExtensionFile(mBook.getBookLink());
        isExistedBook   = StoreUtils.isExistedFile(mBookFile);
        if(isExistedBook) {
            mBtnDownload.setText(R.string.common_txt_read);
            mBtnDownload.setBackgroundResource(R.drawable.background_button_green);
        } else {
            mBtnDownload.setText(R.string.common_txt_download);
            mBtnDownload.setBackgroundResource(R.drawable.background_button_blue);
        }
    }
    class DownloadBookAsync extends AsyncTask<Void, Integer, String> {
        private BookEntity mBookDownload;
        public DownloadBookAsync(BookEntity book) {
            mBookDownload = book;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            String bookLink = RestClient.BOOK_DOWNLOAD;
            if(UserUtils.isUserValid()) {
                String userId   = Constants.mUserInfo.getId();
                bookLink        += userId;
                bookLink        += "/";
            }
            bookLink += mBookDownload.getId();
            Log.e("DOWNLOAD LINK:", bookLink);
            int count;
            String result = null;
            try{
                URL url             = new URL(bookLink);
                URLConnection con   = url.openConnection();
                con.connect();

                long fileLenght     = con.getContentLength();
                long availableSpace = AvailableSpaceUtil.getExternalAvailableSpaceInBytes();

                if(availableSpace < (fileLenght * 2)) {
                    return bookLink;
                }

                InputStream inputStream = new BufferedInputStream(url.openStream(), 8192);
                String fileExtension    = BookUtils.getExtensionFile(bookLink);
                String fileName         = mBookDownload.getId() + fileExtension;
                String outputFolder     = StoreUtils.getBookDirectory();
                StoreUtils.createDirectory(getActivity(), outputFolder, false);

                String outputFile   = outputFolder + fileName;
                result              = outputFile;
                OutputStream outputStream = new FileOutputStream(outputFile);

                byte data[] = new byte[1024];
                long total  = 0;
                while ((count = inputStream.read(data))!= -1) {
                    if(isCancelled()) {
                        result = null;
                        break;
                    }
                    total += count;
                    publishProgress((int) ((total * 100) / fileLenght));
                    outputStream.write(data, 0, count);
                }

                outputStream.flush();
                outputStream.close();
                inputStream.close();

            } catch (IOException ex) {
                ex.printStackTrace();
                result = null;
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if(result != null && result.trim().length() >0 ) {
                mBookFile   = result;
                checkExistedBook();
                mBtnDownload.setEnabled(true);
                Toast.makeText(getActivity(), mBookFile, Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Toast.makeText(getActivity(), "Canceled Download", Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            mBtnDownload.setProgress(values[0]);
        }
    }
}
