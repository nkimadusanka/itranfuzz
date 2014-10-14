package info.itranfuzz.location;

import java.util.List;

import android.content.Context;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class Location {

	LocationManager locationManager;

	public Location(Context a) {
		locationManager = (LocationManager) a
				.getSystemService(Context.LOCATION_SERVICE.toString());
	}

	public LatLng getLocation() {

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

						public void onLocationChanged(Location arg0) {
						}

						@Override
						public void onLocationChanged(
								android.location.Location arg0) {
							// TODO Auto-generated method stub

						}
					});
			android.location.Location location = locationManager
					.getLastKnownLocation(provider);
			if (location != null) {
				double lat = location.getLatitude();
				double lng = location.getLongitude();
				LatLng l = new LatLng(lat, lng);
				return l;
			}
		}
		return (null);
	}

}
