package info.itranfuzz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class Splash extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// fulscreen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.splash);
		Thread backThread = new Thread() {
			@Override
			public void run() {
				try {
					sleep(3000);
					Intent i = new Intent("info.itranfuzz.WEBLOAD");
					startActivity(i);
					finish();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		backThread.start();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

}
