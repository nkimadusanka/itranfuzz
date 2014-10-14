package info.itranfuzz.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONObject;

public class JSONClient {

	private final HttpClient httpClient;
	private HttpPost httpPost;
	private HttpResponse httpResponse;

	public JSONClient() {
		int TIMEOUT_MILLISEC = 10000; // = 10 seconds
		HttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, TIMEOUT_MILLISEC);
		HttpConnectionParams.setSoTimeout(httpParams, TIMEOUT_MILLISEC);
		httpClient = new DefaultHttpClient(httpParams);
	}

	public String doPost(String url, JSONObject jobj) {

		InputStream inputStream = null;
		String result = "";

		httpPost = new HttpPost(url);

		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-type", "application/json");
		try {
			httpPost.setEntity(new StringEntity(jobj.toString()));
			httpResponse = httpClient.execute(httpPost);
			inputStream = httpResponse.getEntity().getContent();
			if (inputStream != null)
				result = convertInputStreamToString(inputStream);
			else
				result = "Did not work!";
		} catch (Exception e) {
			// Log.d("InputStream", e.getLocalizedMessage());
		}
		return result;
	}

	private static String convertInputStreamToString(InputStream inputStream)
			throws IOException {
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(inputStream));
		String line = "";
		String result = "";
		while ((line = bufferedReader.readLine()) != null)
			result += line;

		inputStream.close();
		return result;

	}

}
