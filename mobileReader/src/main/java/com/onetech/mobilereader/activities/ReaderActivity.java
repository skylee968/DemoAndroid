package com.onetech.mobilereader.activities;


import android.app.Activity;
import android.os.Bundle;

import com.onetech.mobilereader.configs.AppConfig.BUNDLE_KEY;

public class ReaderActivity extends Activity {
//        extends BadReader{
//	String path;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            Bundle bundle = new Bundle();
//            bundle = this.getIntent().getExtras();
//            if (bundle != null) {
//                    path = bundle.getString(BUNDLE_KEY.EBOOK_FILE);
//            } else {
//                    return;
//            }
//            //path = "/storage/sdcard0/TheGioiSach/200943.epub";
//            FileInfo dir = Services.getScanner().findParent(new FileInfo(path),
//                            Services.getScanner().getRoot());
//            FileInfo bookInfo = dir.findItemByPathName(path);
//            this.loadDocument(bookInfo);
//    }

}
