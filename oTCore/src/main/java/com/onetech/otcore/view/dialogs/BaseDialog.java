package com.onetech.otcore.view.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap.Config;
import android.graphics.PixelFormat;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.onetech.otcore.R;
import com.onetech.otcore.utils.FontTypefaceUtils;

public class BaseDialog extends Dialog implements
		android.view.View.OnClickListener {

	public interface OnDialogListener {
		public void onPositiveButtonClicked();

		public void onNegativeButtonClicked();

		public void onDefaultButtonClicked();
	}

	private OnDialogListener mListener = null;

	public static final int TYPE_NO_BUTTON 	= 0;
	public static final int TYPE_1_BUTTON 	= 1;
	public static final int TYPE_2_BUTTON 	= 2;

	private TextView mDialogTitle 			= null;
	private View mTitleView 				= null;

	protected LinearLayout layBody 			= null;
	protected LinearLayout lay2Button 		= null;
	protected LinearLayout lay1Button 		= null;
	protected Context mContext = null;
	protected boolean isFullScreen			= false;
	
	public BaseDialog(Context context, String title, int type, int resId) {
		super(context);
		isFullScreen = false;
		initDialog(context, title, type, resId);
	}
	public BaseDialog(Context context, String title, int type, int resId,int resIdStyle) {
		super(context,resIdStyle > 0 ? resIdStyle : R.style.FullScreenDialog);
		isFullScreen = true;
		initDialog(context, title, type, resId);
	}
	private void initDialog(Context context, String title, int type, int resId) {
		mContext = context;
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.base_dialog_layout);

		mDialogTitle 	= (TextView) findViewById(R.id.dialogTitle);

		lay2Button 		= (LinearLayout) findViewById(R.id.up_dialog_base_2_buttons);
		lay1Button 		= (LinearLayout) findViewById(R.id.up_dialog_base_1_button);
		mTitleView 		= (LinearLayout) findViewById(R.id.dialog_base_title);
		layBody 		= (LinearLayout) findViewById(R.id.up_dialog_base_body);
		
		mDialogTitle.setText(title);
		
		if (type == TYPE_NO_BUTTON) {
			lay1Button.setVisibility(View.GONE);
			lay2Button.setVisibility(View.GONE);
		} else if (type == TYPE_1_BUTTON) {
			lay1Button.setVisibility(View.VISIBLE);
			lay2Button.setVisibility(View.GONE);

			findViewById(R.id.up_dialog_base_default).setOnClickListener(this);
		} else {
			lay1Button.setVisibility(View.GONE);
			lay2Button.setVisibility(View.VISIBLE);

			findViewById(R.id.up_dialog_base_positive).setOnClickListener(this);
			findViewById(R.id.up_dialog_base_negative).setOnClickListener(this);
		}

		LayoutInflater inflater = LayoutInflater.from(context);
		inflater.inflate(resId, layBody);
		resize();
	}
	protected void setMainView(Context context, View view){
		layBody.addView(view);
	}
	protected void setBackgroundDialog(int resId){
		LinearLayout view = (LinearLayout) findViewById(R.id.dialog_base_maincontainer);
		view.setBackgroundResource(resId);
	}
	protected void setTitle(String title) {
		mDialogTitle.setText(title);

	}

	protected void setTypeface(Typeface typeFace) {
		try {
			if (typeFace == null) {
				return;
			}
			FontTypefaceUtils.overrideFonts(mContext, typeFace, mDialogTitle);
			FontTypefaceUtils.overrideFonts(mContext, typeFace, lay1Button);
			FontTypefaceUtils.overrideFonts(mContext, typeFace, lay2Button);
			FontTypefaceUtils.overrideFonts(mContext, typeFace, layBody);
		} catch (Exception e) {
			return;
		}
	}

	protected void showHideTitleBar(boolean isShow) {
		mTitleView.setVisibility(isShow ? View.VISIBLE : View.GONE);
	}

	protected void setPositiveButtonTitle(String title) {
		((TextView) findViewById(R.id.up_dialog_base_positive)).setText(title);
	}

	protected void setPositiveButtonTitle(int titleId) {
		((TextView) findViewById(R.id.up_dialog_base_positive))
				.setText(titleId);
	}

	protected void setNegativeButtonTitle(String title) {
		((TextView) findViewById(R.id.up_dialog_base_negative)).setText(title);
	}

	protected void setNegativeButtonTitle(int titleId) {
		((TextView) findViewById(R.id.up_dialog_base_negative))
				.setText(titleId);
	}

	protected void setDefaultButtonTitle(String title) {
		((TextView) findViewById(R.id.up_dialog_base_default)).setText(title);
	}

	protected void setDefaultButtonTitle(int titleId) {
		((TextView) findViewById(R.id.up_dialog_base_default)).setText(titleId);
	}

	public void setOnDialogListener(OnDialogListener listener) {
		mListener = listener;
	}

	@Override
	public void onClick(View view) {
		int id = view.getId();
		if (id == R.id.up_dialog_base_positive) {
			if (mListener != null)
				mListener.onPositiveButtonClicked();
		} else if (id == R.id.up_dialog_base_negative) {
			if (mListener != null)
				mListener.onNegativeButtonClicked();
		} else if (id == R.id.up_dialog_base_default) {
			if (mListener != null)
				mListener.onDefaultButtonClicked();
		}
	}

	public void resize() {
		Window window 						= getWindow();
		DisplayMetrics displayMatrics 		= new DisplayMetrics();
		WindowManager.LayoutParams params 	= new WindowManager.LayoutParams();
		
		window.getWindowManager().getDefaultDisplay().getMetrics(displayMatrics);
		window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		if(isFullScreen){
			WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
			lp.alpha = 1.0f;
			lp.dimAmount = 0.0f;

			Config BUFFER_COLOR_FORMAT = android.graphics.Bitmap.Config.ARGB_8888;
			int PIXEL_FORMAT = (BUFFER_COLOR_FORMAT == android.graphics.Bitmap.Config.RGB_565) ? PixelFormat.RGB_565 : PixelFormat.RGBA_8888;
			
			lp.format = PIXEL_FORMAT;
			lp.gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL;
			lp.horizontalMargin = 0;
			lp.verticalMargin = 0;
			lp.windowAnimations = 0;
			lp.layoutAnimationParameters = null;
			// lp.memoryType = WindowManager.LayoutParams.MEMORY_TYPE_PUSH_BUFFERS;
			getWindow().setAttributes(lp);
		}else{
			setBackgroundDialog(R.drawable.core_base_dialog_background);
			params.copyFrom(getWindow().getAttributes());

			if (displayMatrics.widthPixels > displayMatrics.heightPixels) {
				params.width 	= (int) (displayMatrics.widthPixels * 0.7);
			} else {
				params.width 	= (int) (displayMatrics.widthPixels * 0.9);
			}

			params.height 		= LayoutParams.WRAP_CONTENT;

			window.setAttributes(params);
		}
	}
}
