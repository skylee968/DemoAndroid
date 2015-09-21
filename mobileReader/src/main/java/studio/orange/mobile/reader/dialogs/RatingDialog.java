package studio.orange.mobile.reader.dialogs;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import studio.orange.mobile.reader.R;
import studio.orange.mobile.reader.uitls.CommonUtils;
import com.onetech.otcore.view.dialogs.BaseDialog;


public class RatingDialog extends BaseDialog {
	private Button mBtnRating;
	public RatingDialog(Context context) {
		super(context, "", TYPE_NO_BUTTON, R.layout.dialog_rating, -1);
		showHideTitleBar(false);
		initView();
		initListener();
	}
	private void initView() {
		mBtnRating = (Button) findViewById(R.id.btn_rating);
	}
	private void initListener() {
		mBtnRating.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		super.onClick(view);
		if(view.getId() == R.id.btn_rating) {
			dismiss();
			CommonUtils.openWebUrl("");
		}
	}
}
