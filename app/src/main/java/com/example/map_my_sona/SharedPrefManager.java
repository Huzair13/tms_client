package com.example.map_my_sona;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.firebase.firestore.auth.User;

public class SharedPrefManager {
    private SharedPreferences sharedPreferences;
    private Context context;

    public SharedPrefManager(Context applicationContext) {
        this.context=applicationContext;
        sharedPreferences=context.getSharedPreferences(context.getResources().getString(R.string.login_status_shared_preferences),Context.MODE_PRIVATE);
    }
    public void login_status(boolean status){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean(context.getResources().getString(R.string.login_status_shared_preferences),status);
        editor.commit();
    }
    public boolean read_login_status(){
        boolean status=false;
        status=sharedPreferences.getBoolean(context.getResources().getString(R.string.login_status_shared_preferences),false);
        return status;
    }
}
