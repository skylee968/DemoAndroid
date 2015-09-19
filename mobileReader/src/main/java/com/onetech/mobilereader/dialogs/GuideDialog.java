package com.onetech.mobilereader.dialogs;

import android.content.Context;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import com.onetech.mobilereader.R;
import com.onetech.otcore.view.dialogs.BaseDialog;



public class GuideDialog extends BaseDialog {
	private WebView mWebView;
	private Button mBtnClose;
	public GuideDialog(Context context) {
		super(context, "", TYPE_NO_BUTTON, R.layout.dialog_guide, -1);
		showHideTitleBar(false);
		initView();
		initListener();
	}
	private void initView() {
		mWebView  = (WebView) findViewById(R.id.guide_web_view);
		mBtnClose = (Button) findViewById(R.id.btn_close);
		mWebView.loadUrl("http://google.com");
	}
	private void initListener() {
		mBtnClose.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		super.onClick(view);
		if(view.getId() == R.id.btn_close) {
			dismiss();
		}
	}
}
