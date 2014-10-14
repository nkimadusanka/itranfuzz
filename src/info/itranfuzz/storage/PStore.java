package info.itranfuzz.storage;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PStore {

	SharedPreferences pref;
	Editor edit;

	public PStore(SharedPreferences pref, Editor edit) {
		this.pref = pref;
		this.edit = edit;
	}

	// check user login or not
	public boolean checklogin() {
		return (pref.getBoolean("isLog", false));
	}

	// save user email and password and make login to true
	public boolean saveSession(String email, String password) {
		edit.putString("email", email);
		edit.putString("password", password);
		edit.putBoolean("isLog", true);

		return (edit.commit());
	}

	// get Email
	public String getEmail() {
		return (pref.getString("email", null));
	}

}
