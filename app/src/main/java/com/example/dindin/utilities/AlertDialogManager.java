package com.example.dindin.utilities;

/**
 * Created by Kevin on 11/19/2016.
 * Handles errorMessages and internetConnectionErrors
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.example.dindin.R;


public class AlertDialogManager {

    public static void errorMessage(Context context, String title,
                                    String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);

        builder.setPositiveButton(
                context.getResources().getString(R.string.okbuttontext),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog alert = builder.create();
        alert.setCancelable(false);
        alert.show();
    }

    /**
     * Dialog to show No internet connection
     */
    public static void internetConnectionErrorMsg(final Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Internet Connection Error");
        builder.setMessage("Please connect to working Internet connection.");

        builder.setNegativeButton(
                activity.getResources().getString(R.string.okbuttontext),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        activity.finish();

                    }
                });

        AlertDialog alert = builder.create();
        alert.setCancelable(false);
        alert.show();
    }

}