package studio.orange.mobile.reader.dialogs;

import android.app.Activity;

import studio.orange.mobile.reader.R;
import com.onetech.otcore.view.dialogs.BaseDialog;



public class UpdateDialog extends BaseDialog {
	private boolean isForceUpdate=false;
	private Activity activity=null;
	private String packageName="";

	public UpdateDialog(Activity context,boolean _isForceUpdate,String _packageName){
		super(context,context.getString(R.string.dialog_txt_update_title),TYPE_2_BUTTON, R.layout.dialog_update);
		activity=context;
		isForceUpdate=_isForceUpdate;	
		packageName=_packageName;
		if(isForceUpdate){
			setNegativeButtonTitle(R.string.common_txt_btn_close);
		}else{
			setNegativeButtonTitle(R.string.common_txt_btn_cancel);
		}
		if(isForceUpdate){
			setPositiveButtonTitle(R.string.common_txt_btn_ok);
		}else{
			setPositiveButtonTitle(R.string.common_txt_btn_ok);
		}
		setDefaultButtonTitle(R.string.common_txt_btn_ok);
	}
	@Override
    public void onStart() {
        super.onStart();
    }
}
