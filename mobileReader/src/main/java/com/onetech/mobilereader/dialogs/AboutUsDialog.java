package com.onetech.mobilereader.dialogs;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.onetech.mobilereader.R;
import com.onetech.otcore.view.dialogs.BaseDialog;



public class AboutUsDialog extends BaseDialog {
	private Button mBtnClose;
	public AboutUsDialog(Context context) {
		super(context, "", TYPE_NO_BUTTON, R.layout.dialog_about_us, -1);
		showHideTitleBar(false);
		initView();
		initListener();
	}
	private void initView() {
		mBtnClose = (Button) findViewById(R.id.btn_close);
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
