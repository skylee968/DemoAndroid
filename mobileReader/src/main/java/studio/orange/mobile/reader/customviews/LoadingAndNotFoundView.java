package studio.orange.mobile.reader.customviews;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import studio.orange.mobile.reader.R;

public class LoadingAndNotFoundView extends RelativeLayout implements OnClickListener{
	private Context mContext;
	private View mLoadingView;
	private View mNotFoundView;
	private View mBtnRetry;

	private TextView mTxtNotfoundMessage;
	private TextView mTxtLoadingMessage;

	private LoadingAndNotFoundListener mListener;

	public interface LoadingAndNotFoundListener {
		void onReloadClicked();
	}
	public LoadingAndNotFoundView(Context context) {
		super(context);
		init(context);
	}

	public LoadingAndNotFoundView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public LoadingAndNotFoundView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context);
	}

	@SuppressLint("NewApi")
	public LoadingAndNotFoundView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		init(context);
	}

	private void init(Context context) {
		mContext = context;
		initView();
		initListener();
	}
	private void initView() {
		inflate(mContext, R.layout.layout_loading_and_not_found, this);
		mLoadingView			= (RelativeLayout) findViewById(R.id.mbr_loading_container);
		mNotFoundView			= (RelativeLayout) findViewById(R.id.mbr_not_found_container);
		mBtnRetry				= (Button) mNotFoundView.findViewById(R.id.mbr_btn_retry);
		mTxtNotfoundMessage		= (TextView) findViewById(R.id.mbr_txt_not_found_message);
		mTxtLoadingMessage 		= (TextView) findViewById(R.id.mbr_loading_message);
		RelativeLayout.LayoutParams params = new LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
		this.setLayoutParams(params);
	}
	private void initListener() {
		mBtnRetry.setOnClickListener(this);
	}
	public void setOnLoadingAndNotFoundClickListener(LoadingAndNotFoundListener _onListener) {
		mListener = _onListener;
	}
	public void switchView(boolean isLoading, boolean isNotFound) {
		mLoadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
		mNotFoundView.setVisibility(isNotFound ? View.VISIBLE : View.GONE);
	}
	public void setRetryButtonVisibility(boolean isShow) {
		mBtnRetry.setVisibility(isShow ? View.VISIBLE : View.GONE);
	}
	public void setNotFoundMessage(String message) {
		mTxtNotfoundMessage.setText(message);
	}
	public void setLoadingMessage(String message) {
		mTxtLoadingMessage.setText(message);
	}
	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.mbr_btn_retry:
			if(mListener != null) {
				mListener.onReloadClicked();
			}
			break;
		default:
			break;
		}
	}
}

