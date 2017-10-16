package com.alam.quickbloxchatdemo;

import android.content.Context;
import android.content.SharedPreferences;



public class UserSession {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "user_pref";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "is_user_logged_in";

    public static final String KEY_USER_TOKEN = "user_token";
    public static final String KEY_OPPONENT_ID_CURRENT_USER = "opponent_id";
    public static final String KEY_USER_ID = "user_id";
    public static final String KEY_FCM_TOKEN = "fcm_token";
    public static final String KEY_PERMISSION_DIALOG = "perm_dialog";
    public static final String KEY_QUIKBLOX_ID = "quikblox_id";

    public  static  final String KEY_USERIMAGE="";


    public UserSession(Context _context) {
        this.context = _context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     */
    public void createUserSession(String user_token) {

        // Log.d("user_token",user_token);
        editor.putString(KEY_USER_TOKEN, user_token);
        editor.putBoolean(IS_LOGIN, true);

        // commit changes
        editor.commit();
    }

    public void createFCMToken(String fcmToken) {
        editor.putString(KEY_FCM_TOKEN, fcmToken);
        editor.commit();
    }

    public void setQuikBloxID(String userID) {
        editor.putString(KEY_QUIKBLOX_ID, userID);
        editor.commit();
    }

    public void setUserImage(String userimage){
        editor.putString(KEY_USERIMAGE, userimage);
        editor.commit();
    }

    public String getUserimage(){
        return pref.getString(KEY_USERIMAGE,"");
    }

    public String getQuikbloxId() {
        return pref.getString(KEY_QUIKBLOX_ID, "0");
    }

    public void setUserID(String userID) {
        editor.putString(KEY_USER_ID, userID);
        editor.commit();
    }

    public void setPermissionDialog(Boolean permStatus) {
        editor.putBoolean(KEY_PERMISSION_DIALOG, permStatus);
        editor.commit();
    }

    public void setOpponentID(String opponent_id) {
        editor.putString(KEY_OPPONENT_ID_CURRENT_USER, opponent_id);
        editor.commit();
    }

    public String getUserId() {
        return pref.getString(KEY_USER_ID, "");
    }

    public String getUserToken() {
        return pref.getString(KEY_USER_TOKEN, "");
    }

    public String getFCMToken() {
        return pref.getString(KEY_FCM_TOKEN, "");
    }

    public String getCurrentUserOpponentId() {
        return pref.getString(KEY_OPPONENT_ID_CURRENT_USER, "0");
    }

    public Boolean getPermissionDialogStatus() {
        return pref.getBoolean(KEY_PERMISSION_DIALOG, false);
    }

    /**
     * Clear session details
     */

    public void clearUserSession() { // Clearing all data from Shared
        editor.remove(KEY_USER_TOKEN);
        editor.remove(IS_LOGIN);
        editor.remove(KEY_OPPONENT_ID_CURRENT_USER);
        editor.remove(KEY_USER_ID);
        editor.commit();
    }

    public void logout() { // Clearing all data from Shared

        editor.remove(IS_LOGIN);
        editor.commit();
    }


    // Get Login State
    public boolean isUserLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }


}
