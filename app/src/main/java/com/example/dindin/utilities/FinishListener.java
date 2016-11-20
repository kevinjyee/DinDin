package com.example.dindin.utilities;

/**
 * Created by Kevin on 11/19/2016.
 */

import android.app.Activity;
import android.content.DialogInterface;

public final class FinishListener
        implements DialogInterface.OnClickListener, DialogInterface.OnCancelListener, Runnable {

    private final Activity activityToFinish;

    public FinishListener(Activity activityToFinish)
    {
        this.activityToFinish = activityToFinish;
    }

    @Override
    public void onCancel(DialogInterface dialogInterface)
    {
        run();
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i)
    {
        run();
    }

    @Override
    public void run()
    {
        activityToFinish.finish();
    }

}