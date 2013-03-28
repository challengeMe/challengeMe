package ch.comem.connexion;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class AppPrefs {
	 private static final String USER_PREFS = "USER_PREFS";
	 private SharedPreferences appSharedPrefs;
	 private SharedPreferences.Editor prefsEditor;
	 private String userName = "userNamePrefs";
	 private String userId = "userIdPrefs";
	 
	public AppPrefs(Context context){
	 this.appSharedPrefs = context.getSharedPreferences(USER_PREFS, Activity.MODE_PRIVATE);
	 this.prefsEditor = appSharedPrefs.edit();
	 }
	public int getUser_id() {
	 return appSharedPrefs.getInt(userId, -1);
	 }
	 
	public void setUser_id(int _user_id) {
	 prefsEditor.putInt(userId, _user_id).commit();
	}
	public String getUser_name() {
	 return appSharedPrefs.getString(userName, "unkown");
	 }
	 
	 public void setUser_name( String _user_name) {
	 prefsEditor.putString(userName, _user_name).commit();
	 }
	 
	}