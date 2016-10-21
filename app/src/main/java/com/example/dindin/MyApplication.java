package com.example.dindin;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Stefan on 10/19/2016.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        printHashKey();
    }

    public void printHashKey(){

        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.example.dindin",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String temp = Base64.encodeToString(md.digest(), Base64.DEFAULT);
                Log.d("YeshHash:", temp);
                System.out.println(temp);
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }

}
