package com.onetech.otcore.view.customviews;

import com.onetech.otcore.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


public class ProgressAnimation extends RelativeLayout{
	private int layout_height = 0;
    private int layout_width = 0;
    
    private int animBackgroundResources=0;
    private ImageView mImageView = null;
    private AnimationDrawable progressAnimationDrawable;
    
	public ProgressAnimation(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initViews(context, attrs);
	}
	public ProgressAnimation(Context context, AttributeSet attrs) {
		super(context, attrs);
		initViews(context, attrs);
	}
	public ProgressAnimation(Context context) {
		super(context);
		inflate(context, R.layout.meta__progress_wheel, this);
	}
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		layout_width = w;
        layout_height = h;
        setupBounds();
	}
	private void setupBounds() {
		android.widget.LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(layout_width, layout_height);
		if(mImageView!=null){
			mImageView.setLayoutParams(layoutParams);
		}
		
        // Width should equal to Height, find the min value to steup the circle
        //int minValue = Math.min(layout_width, layout_height);

        // Calc the Offset if needed
        //int xOffset = layout_width - minValue;
        //int yOffset = layout_height - minValue;
	}
	private void initViews(Context context, AttributeSet attrs){
		TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
				R.styleable.ProgressAnimation, 0, 0);
		try {
			animBackgroundResources = a.getResourceId(R.styleable.ProgressAnimation_animDrawable, R.anim.anim__progress_animation);
			inflate(context, R.layout.meta__progress_wheel,this);
			mImageView=(ImageView) findViewById(R.id.progressWheelImage);
			mImageView.setBackgroundResource(animBackgroundResources);
			progressAnimationDrawable=(AnimationDrawable)mImageView.getBackground();
			progressAnimationDrawable.setOneShot(false);
		} finally {
			a.recycle();
		}
	}
	public void start(){
		if(progressAnimationDrawable!=null && !progressAnimationDrawable.isRunning()){
			progressAnimationDrawable.start();
		}
	}
	public void stop(){
		if(progressAnimationDrawable!=null&& progressAnimationDrawable.isRunning()){
			progressAnimationDrawable.stop();
		}
	}
	public boolean isRunning(){
		if(progressAnimationDrawable!=null){
			return progressAnimationDrawable.isRunning();
		}
		return false;
	}
}
