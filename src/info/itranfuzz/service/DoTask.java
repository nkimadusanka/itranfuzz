package info.itranfuzz.service;

import java.util.TimerTask;

import android.app.Activity;
import android.widget.Toast;

public class DoTask extends TimerTask {

	private final Activity cont;

	public DoTask(Activity c) {
		this.cont = c;
	}

	@Override
	public void run() {
		// and at the end show info
		cont.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(cont, "Running task", Toast.LENGTH_LONG).show();
			}
		});

	}
}
