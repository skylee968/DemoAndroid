package studio.orange.mobile.reader.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import studio.orange.mobile.reader.R;
import studio.orange.mobile.reader.dialogs.DirectoryDialog;
import studio.orange.mobile.reader.dialogs.DirectoryDialog.ResultDirectorySelected;

public class TestActivity extends Activity {
	private DirectoryDialog mDirectoryDialog = null;
	private ResultDirectorySelected mResultDirectory=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_reader);
        
        Button mBtnOpenDirectory = null;
        mBtnOpenDirectory=(Button) findViewById(R.id.openDirectoryBtn);
        mBtnOpenDirectory.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showSelectFileDialog();
			}
		});
//        String path = "/storage/sdcard0/DacNhanTam-DaleCarnegie.epub";
//        //String path = "/storage/sdcard0/Dac-Nhan-Tam-Dale-Carnegie.prc";
//        Intent intent=new Intent(TestActivity.this,ReaderActivity.class);
//		intent.putExtra(BUNDLE_KEY.EBOOK_FILE, path);
//		startActivity(intent);	
		
        mResultDirectory=new ResultDirectorySelected() {
			@Override
			public void onChooseDirectory(String dir) {
//				//Toast.makeText(getApplicationContext(), dir, Toast.LENGTH_LONG).show();
//				Intent intent=new Intent(TestActivity.this,ReaderActivity.class);
//				intent.putExtra(BUNDLE_KEY.EBOOK_FILE, dir);
//				startActivity(intent);				
			}
		};
	}
	private void showSelectFileDialog(){
		mDirectoryDialog = new DirectoryDialog(TestActivity.this, mResultDirectory, null);
	}
}
