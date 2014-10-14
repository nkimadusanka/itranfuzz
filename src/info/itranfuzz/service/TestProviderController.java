package info.itranfuzz.service;

import info.itranfuzz.R;

import java.util.List;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TestProviderController extends Activity {

	LocationManager locationManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.testservice);

		// String location_context = Context.LOCATION_SERVICE;
		// locationManager = (LocationManager)
		// getSystemService(location_context);
		// testProviders();
	}

	public void testProviders() {
		TextView tv = (TextView) findViewById(R.id.lTV);
		StringBuilder sb = new StringBuilder("Enabled Providers:");

		List<String> providers = locationManager.getProviders(true);

		for (String provider : providers) {
			locationManager.requestLocationUpdates(provider, 1000, 0,
					new LocationListener() {

						@Override
						public void onStatusChanged(String arg0, int arg1,
								Bundle arg2) {
						}

						@Override
						public void onProviderEnabled(String arg0) {
						}

						@Override
						public void onProviderDisabled(String arg0) {
						}

						@Override
						public void onLocationChanged(Location arg0) {
						}
					});
			sb.append("\n").append(provider).append(":");

			Location location = locationManager.getLastKnownLocation(provider);
			if (location != null) {
				double lat = location.getLatitude();
				double lng = location.getLongitude();
				sb.append(lat).append(",").append(lng);
			} else {
				sb.append("No Location");
			}

		}
		tv.setText(sb);
	}

	// Method to start the service
	public void startService(View view) {

		startService(new Intent(getBaseContext(), LocationService.class));

		if (LocationService.RSTATE == 0) {
			Intent intent = new Intent(this, LocationService.class);
			PendingIntent pintent = PendingIntent
					.getService(this, 0, intent, 0);
			AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
			alarm.setRepeating(AlarmManager.RTC_WAKEUP, 10000, 30 * 1000,
					pintent);
		}

	}

	// Method to stop the service
	public void stopService(View view) {
		Intent intent = new Intent(this, LocationService.class);
		PendingIntent pintent = PendingIntent.getService(this, 0, intent, 0);
		AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		alarm.cancel(pintent);
		stopService(new Intent(getBaseContext(), LocationService.class));
	}

}
