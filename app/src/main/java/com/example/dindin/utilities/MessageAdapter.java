package com.example.dindin.utilities;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Kevin on 12/2/2016.
 */

public class MessageAdapter {

    // Shared Preferences
    private SharedPreferences pref;
    // Editor for Shared preferences
    private SharedPreferences.Editor editor;
    // Context
    private Context _context;
    public MessageAdapter(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences("dindin", 0);
        editor = pref.edit();
    }


    public void setLastMessage(String key, String lastMessage) {
        editor.putString(key, lastMessage);
        // commit changes
        editor.commit();
    }

    public String getLastMessage(String key) {
        return pref.getString(key, null);
    }
}
