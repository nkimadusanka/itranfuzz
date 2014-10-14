package info.itranfuzz.service;

import info.itranfuzz.WebLoad;
import info.itranfuzz.location.LatLng;
import info.itranfuzz.location.Location;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class LocationService extends Service {

	public static int RSTATE = 0;
	public static int count = 0;
	public static final String MY_SERVICE = "info.itranfuzz.service.LOCATIONSERVICE";

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		RSTATE = 1;
		String lText = "";
		JSONClient jClient = new JSONClient();

		Location loc = new Location(LocationService.this);
		LatLng p = loc.getLocation();

		if (p != null) {
			lText = "Latitude: " + p.getLat() + "\nLongtitude:" + p.getLng()
					+ "\n";
			String json = "";

			try {
				JSONObject jsonObject = new JSONObject();
				// jsonObject.accumulate("email", WebLoad.STORE.getEmail());
				jsonObject.put("email", "b@gmail.com");
				jsonObject.put("lat", p.getLat());
				jsonObject.put("lng", p.getLng());

				json = jsonObject.toString();

				System.out.println(jClient.doPost(WebLoad.ROOTURL
						+ "/donor_controller/updatelocation", jsonObject)
						+ "");

			} catch (JSONException e) {
				System.out.println("Json exception occur");
			}

		}

		Toast.makeText(this, lText + "Service Started " + (count++),
				Toast.LENGTH_LONG).show();
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		RSTATE = 0;
		Toast.makeText(this, "Service Stop", Toast.LENGTH_LONG).show();
	}

}
