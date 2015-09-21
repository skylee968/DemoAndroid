package studio.orange.mobile.reader.dialogs;

import android.app.Activity;

import studio.orange.mobile.reader.R;

import com.onetech.otcore.view.dialogs.BaseDialog;



public class ExitDialog extends BaseDialog {
	private ExitHandler mAdHandler=null;
	public interface ExitHandler {
		public void doExit();
	}
	public ExitDialog(Activity context, ExitHandler _handler) {
		super(context, context.getString(R.string.dialog_txt_exit_title),
				TYPE_2_BUTTON, R.layout.dialog_exit);
		//activity = context;
		setNegativeButtonTitle(R.string.common_txt_btn_cancel);
		setPositiveButtonTitle(R.string.common_txt_btn_ok);
		setDefaultButtonTitle(R.string.common_txt_btn_ok);
		mAdHandler=_handler;
		setOnDialogListener(new OnDialogListener() {
			@Override
			public void onPositiveButtonClicked() {
				dismiss();
				mAdHandler.doExit();
			}

			@Override
			public void onNegativeButtonClicked() {
				dismiss();
			}

			@Override
			public void onDefaultButtonClicked() {
				dismiss();
			}
		});
	}
}
