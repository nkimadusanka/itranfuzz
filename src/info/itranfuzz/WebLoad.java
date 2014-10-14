package info.itranfuzz;

import info.itranfuzz.service.LocationService;
import info.itranfuzz.storage.PStore;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebLoad extends Activity {

	public static final String MyPREFERENCES = "login";
	public static final String ROOTURL = "http://10.0.2.2/itransfusion.lk";
	public static PStore STORE;

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.webview);

		// initializing webview to shared preferences
		STORE = new PStore(getSharedPreferences(MyPREFERENCES,
				Context.MODE_WORLD_READABLE), getSharedPreferences(
				MyPREFERENCES, Context.MODE_WORLD_WRITEABLE).edit());

		WebView wb = (WebView) findViewById(R.id.web_main);
		wb.getSettings().setJavaScriptEnabled(true);
		wb.setVerticalScrollBarEnabled(false);

		wb.setWebViewClient(new WebViewClient() {

			@Override
			public void onReceivedError(WebView view, int errorCod,
					String description, String failingUrl) {
				System.out
						.println("Your Internet Connection May not be active Or:"
								+ description);
			}
		});

		// set java script lisener
		wb.addJavascriptInterface(new WebAppInterface(this), "Android");

		if (STORE.checklogin()) {
			wb.loadUrl("file:///android_asset/index.html");

			// starting web service
			startService(new Intent(getBaseContext(), LocationService.class));
			if (LocationService.RSTATE == 0) {
				Intent intent = new Intent(this, LocationService.class);
				PendingIntent pintent = PendingIntent.getService(this, 0,
						intent, 0);
				AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
				alarm.setRepeating(AlarmManager.RTC_WAKEUP, 10000, 30 * 1000,
						pintent);
			}
		} else {
			wb.loadUrl("file:///android_asset/login.html");
		}
		// loading web service

	}
}
