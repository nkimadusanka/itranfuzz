package info.itranfuzz;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class WebLoad extends Activity {

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview);

		WebView wb = (WebView) findViewById(R.id.web_main);
		wb.getSettings().setJavaScriptEnabled(true);
		wb.setVerticalScrollBarEnabled(false);
		wb.loadUrl("file:///android_asset/login.html");
	}

}
