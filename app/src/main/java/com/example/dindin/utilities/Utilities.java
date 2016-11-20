

        package com.example.dindin.utilities;

        import java.io.BufferedInputStream;
        import java.io.BufferedReader;
        import java.io.ByteArrayInputStream;
        import java.io.ByteArrayOutputStream;
        import java.io.File;
        import java.io.FileInputStream;
        import java.io.FileNotFoundException;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.io.OutputStream;
        import java.io.UnsupportedEncodingException;
        import java.net.HttpURLConnection;
        import java.net.MalformedURLException;
        import java.net.SocketException;
        import java.net.URL;
        import java.net.URLConnection;
        import java.nio.channels.FileChannel;
        import java.text.Format;
        import java.text.ParseException;
        import java.text.SimpleDateFormat;
        import java.util.ArrayList;
        import java.util.Calendar;
        import java.util.Date;
        import java.util.List;


        import android.annotation.SuppressLint;
        import android.annotation.TargetApi;
        import android.app.Activity;
        import android.app.AlertDialog;
        import android.app.Dialog;
        import android.app.ProgressDialog;
        import android.content.Context;
        import android.content.CursorLoader;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.content.pm.ActivityInfo;
        import android.content.pm.PackageInfo;
        import android.content.pm.PackageManager;
        import android.content.pm.PackageManager.NameNotFoundException;
        import android.content.res.Configuration;
        import android.database.Cursor;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.graphics.Canvas;
        import android.graphics.Color;
        import android.graphics.Matrix;
        import android.graphics.Paint;
        import android.graphics.Point;
        import android.graphics.PorterDuff.Mode;
        import android.graphics.PorterDuffXfermode;
        import android.graphics.Rect;
        import android.graphics.RectF;
        import android.net.ConnectivityManager;
        import android.net.NetworkInfo;
        import android.net.Uri;
        import android.os.Build;
        import android.os.Environment;
        import android.os.SystemClock;
        import android.provider.MediaStore;
        import android.telephony.TelephonyManager;
        import android.util.Base64;
        import android.util.Log;
        import android.view.Display;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.Window;
        import android.view.WindowManager;
        import android.webkit.URLUtil;
        import android.widget.Chronometer;
        import android.widget.RelativeLayout;
        import android.widget.TextView;

        import com.example.dindin.R;


        public class Utilities {
    private static final String TAG = "Utilities";
    private static Bitmap bgimage;

    private static Bitmap CallImage;
    private static Uri uri;
    private static int time;
    private static String color;
    public static Boolean isDirectoryIsCreated = false;
    public static float imagexPostion;
    public static float imageyPostion;
    public static float pagewidth;
    public static float pageheigth;
    private File createdirectoty;
    private File createdFileName;
    private InputStream is;

    SimpleDateFormat inFormat = null;
    // private String datestr;
    Date date = null;
    SimpleDateFormat outFormat;
    private static boolean mDebugLog = true;
    private static String mDebugTag = "Utilities";

    void logDebug(String msg) {

        if (mDebugLog) {
            Log.d(mDebugTag, msg);
        }
    }

    void logError(String msg) {

        if (mDebugLog) {
            Log.e(mDebugTag, msg);
        }
    }



    public String getCurrentGmtTime() {
        String curentdateTime = null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        curentdateTime = sdf.format(new Date());

        return curentdateTime;
    }

    public Bitmap getCircleBitmap(Bitmap bitmap, int pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xffff0000;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setFilterBitmap(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawOval(rectF, paint);

        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth((float) 4);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;

    }

    public int getStatusBarHeight(Activity activity) {
        int result = 0;
        int resourceId = activity.getResources().getIdentifier(
                "status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = activity.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    @SuppressLint("NewApi")
    public static int getWidth(Context mContext) {
        int width = 0;
        WindowManager wm = (WindowManager) mContext
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        if (Build.VERSION.SDK_INT > 12) {
            Point size = new Point();
            display.getSize(size);
            width = size.x;
        } else {
            width = display.getWidth(); // deprecated
        }
        return width;
    }

    @SuppressLint("NewApi")
    public static int getHeight(Context mContext) {
        int height = 0;
        WindowManager wm = (WindowManager) mContext
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        if (Build.VERSION.SDK_INT > 12) {
            Point size = new Point();
            display.getSize(size);
            height = size.y;
        } else {
            height = display.getHeight(); // deprecated
        }
        return height;
    }

    public int addsHeight(Activity activity) {

        int screenHeight = getHeight(activity);
        int screenWidth = getWidth(activity);

        int topMatgin = 0;

        if ((screenHeight <= 500 && screenHeight >= 480)
                && (screenWidth <= 340 && screenWidth >= 300)) {



        }

        else if ((screenHeight <= 400 && screenHeight >= 300)
                && (screenWidth <= 240 && screenWidth >= 220)) {




        }

        else if ((screenHeight <= 840 && screenHeight >= 780)
                && (screenWidth <= 500 && screenWidth >= 440)) {




        } else if ((screenHeight <= 1280 && screenHeight >= 840)
                && (screenWidth <= 720 && screenWidth >= 500)) {


        } else {

        }
        return topMargin;
    }





    public int[] getImageHeightAndWidthForMatchedUser(Activity activity) {
        // //Log.i(TAG, "getImageHeightAndWidth");

        int imageHeightAndWidth[] = new int[2];
        int screenHeight = getHeight(activity);
        int screenWidth = getWidth(activity);
        // //Log.i(TAG, "getImageHeightAndWidth  screenHeight "+screenHeight);
        // //Log.i(TAG, "getImageHeightAndWidth  screenWidth  "+screenWidth);
        int imagehiegth;
        int imagewidth;
        if ((screenHeight <= 500 && screenHeight >= 480)
                && (screenWidth <= 340 && screenWidth >= 300)) {
            // //Log.i(TAG, "getImageHeightAndWidth mdpi");
            imagehiegth = 100;
            imagewidth = 100;
            imageHeightAndWidth[0] = imagehiegth;
            imageHeightAndWidth[1] = imagewidth;

        }

        else if ((screenHeight <= 400 && screenHeight >= 300)
                && (screenWidth <= 240 && screenWidth >= 220))

        {

            // //Log.i(TAG, "getImageHeightAndWidth ldpi");
            imagehiegth = 120;
            imagewidth = 120;
            imageHeightAndWidth[0] = imagehiegth;
            imageHeightAndWidth[1] = imagewidth;
        }

        else if ((screenHeight <= 840 && screenHeight >= 780)
                && (screenWidth <= 500 && screenWidth >= 440)) {

            // //Log.i(TAG, "getImageHeightAndWidth hdpi");
            imagehiegth = 480;
            imagewidth = 440;
            imageHeightAndWidth[0] = imagehiegth;
            imageHeightAndWidth[1] = imagewidth;
        } else if ((screenHeight <= 1280 && screenHeight >= 840)
                && (screenWidth <= 720 && screenWidth >= 500)) {

            // //Log.i(TAG, "getImageHeightAndWidth xdpi");
            imagehiegth = 600;
            imagewidth = 760;
            imageHeightAndWidth[0] = imagehiegth;
            imageHeightAndWidth[1] = imagewidth;
        } else {
            imagehiegth = 200;
            imagewidth = 200;
            imageHeightAndWidth[0] = imagehiegth;
            imageHeightAndWidth[1] = imagewidth;
        }

        return imageHeightAndWidth;
    }

    public int[] getTopMarginForInviteLayoutAndText(Activity activity) {
        // //Log.i(TAG, "getImageHeightAndWidth");

        int imageHeightAndWidth[] = new int[2];
        int screenHeight = getHeight(activity);
        int screenWidth = getWidth(activity);
        // //Log.i(TAG, "getImageHeightAndWidth  screenHeight "+screenHeight);
        // //Log.i(TAG, "getImageHeightAndWidth  screenWidth  "+screenWidth);
        int imagehiegth;
        int imagewidth;
        if ((screenHeight <= 500 && screenHeight >= 480)
                && (screenWidth <= 340 && screenWidth >= 300)) {
            // //Log.i(TAG, "getImageHeightAndWidth mdpi");
            imagehiegth = 100;
            imagewidth = 100;
            imageHeightAndWidth[0] = imagehiegth;
            imageHeightAndWidth[1] = imagewidth;

        }

        else if ((screenHeight <= 400 && screenHeight >= 300)
                && (screenWidth <= 240 && screenWidth >= 220))

        {

            // //Log.i(TAG, "getImageHeightAndWidth ldpi");
            imagehiegth = 120;
            imagewidth = 120;
            imageHeightAndWidth[0] = imagehiegth;
            imageHeightAndWidth[1] = imagewidth;
        }

        else if ((screenHeight <= 840 && screenHeight >= 780)
                && (screenWidth <= 500 && screenWidth >= 440)) {

            // //Log.i(TAG, "getImageHeightAndWidth hdpi");
            imagehiegth = 50;
            imagewidth = 20;
            imageHeightAndWidth[0] = imagehiegth;
            imageHeightAndWidth[1] = imagewidth;
        } else if ((screenHeight <= 1280 && screenHeight >= 840)
                && (screenWidth <= 720 && screenWidth >= 500)) {

            // //Log.i(TAG, "getImageHeightAndWidth xdpi");
            imagehiegth = 100;
            imagewidth = 70;
            imageHeightAndWidth[0] = imagehiegth;
            imageHeightAndWidth[1] = imagewidth;
        } else {
            imagehiegth = 200;
            imagewidth = 200;
            imageHeightAndWidth[0] = imagehiegth;
            imageHeightAndWidth[1] = imagewidth;
        }

        return imageHeightAndWidth;
    }

    // imageLayoutHeightandWidth
    public int[] getImageHeightAndWidthForProFileImageHomsecreen(
            Activity activity) {
        // //Log.i(TAG, "getImageHeightAndWidth");

        int imageHeightAndWidth[] = new int[2];
        int screenHeight = getHeight(activity);
        int screenWidth = getWidth(activity);
        // //Log.i(TAG, "getImageHeightAndWidth  screenHeight "+screenHeight);
        // //Log.i(TAG, "getImageHeightAndWidth  screenWidth  "+screenWidth);
        int imagehiegth;
        int imagewidth;
        if ((screenHeight <= 500 && screenHeight >= 480)
                && (screenWidth <= 340 && screenWidth >= 300)) {
            // //Log.i(TAG, "getImageHeightAndWidth mdpi");
            imagehiegth = 100;
            imagewidth = 100;
            imageHeightAndWidth[0] = imagehiegth;
            imageHeightAndWidth[1] = imagewidth;

        }

        else if ((screenHeight <= 400 && screenHeight >= 300)
                && (screenWidth <= 240 && screenWidth >= 220))

        {

            // //Log.i(TAG, "getImageHeightAndWidth ldpi");
            imagehiegth = 120;
            imagewidth = 120;
            imageHeightAndWidth[0] = imagehiegth;
            imageHeightAndWidth[1] = imagewidth;
        }

        else if ((screenHeight <= 840 && screenHeight >= 780)
                && (screenWidth <= 500 && screenWidth >= 440)) {

            // //Log.i(TAG, "getImageHeightAndWidth hdpi");
            imagehiegth = 150;
            imagewidth = 150;
            imageHeightAndWidth[0] = imagehiegth;
            imageHeightAndWidth[1] = imagewidth;
        } else if ((screenHeight <= 1280 && screenHeight >= 840)
                && (screenWidth <= 720 && screenWidth >= 500)) {

            // //Log.i(TAG, "getImageHeightAndWidth xdpi");
            imagehiegth = 200;
            imagewidth = 200;
            imageHeightAndWidth[0] = imagehiegth;
            imageHeightAndWidth[1] = imagewidth;
        } else {
            imagehiegth = 200;
            imagewidth = 200;
            imageHeightAndWidth[0] = imagehiegth;
            imageHeightAndWidth[1] = imagewidth;
        }

        return imageHeightAndWidth;
    }

    public int[] imageLayoutHeightandWidth(Activity activity) {
        // //Log.i(TAG, "getImageHeightAndWidth");

        int imageHeightAndWidth[] = new int[5];
        int screenHeight = getHeight(activity);
        int screenWidth = getWidth(activity);
        // //Log.i(TAG, "getImageHeightAndWidth  screenHeight "+screenHeight);
        // //Log.i(TAG, "getImageHeightAndWidth  screenWidth  "+screenWidth);
        int imagehiegth;
        int imagewidth;
        int topMargin;
        int likedisliketopMarging;
        int leftMargin;
        if ((screenHeight <= 500 && screenHeight >= 480)
                && (screenWidth <= 340 && screenWidth >= 300)) {
            // //Log.i(TAG, "getImageHeightAndWidth mdpi");
            imagehiegth = 100;
            imagewidth = 100;
            imageHeightAndWidth[0] = imagehiegth;
            imageHeightAndWidth[1] = imagewidth;

        }

        else if ((screenHeight <= 400 && screenHeight >= 300)
                && (screenWidth <= 240 && screenWidth >= 220))

        {

            // //Log.i(TAG, "getImageHeightAndWidth ldpi");
            imagehiegth = 120;
            imagewidth = 120;

            imageHeightAndWidth[0] = imagehiegth;
            imageHeightAndWidth[1] = imagewidth;
        }

        else if ((screenHeight <= 840 && screenHeight >= 780)
                && (screenWidth <= 500 && screenWidth >= 440)) {

            // //Log.i(TAG, "getImageHeightAndWidth hdpi");
            imagehiegth = 480;
            imagewidth = 430;
            topMargin = 20;
            likedisliketopMarging = 520;
            leftMargin = 25;
            imageHeightAndWidth[0] = imagehiegth;
            imageHeightAndWidth[1] = imagewidth;
            imageHeightAndWidth[2] = topMargin;
            imageHeightAndWidth[3] = likedisliketopMarging;
            imageHeightAndWidth[4] = leftMargin;
        } else if ((screenHeight <= 1280 && screenHeight >= 840)
                && (screenWidth <= 720 && screenWidth >= 500)) {

            // //Log.i(TAG, "getImageHeightAndWidth xdpi");
            imagehiegth = 600;
            imagewidth = 645;
            topMargin = 100;
            likedisliketopMarging = 750;
            leftMargin = 32;
            imageHeightAndWidth[0] = imagehiegth;
            imageHeightAndWidth[1] = imagewidth;
            imageHeightAndWidth[2] = topMargin;
            imageHeightAndWidth[3] = likedisliketopMarging;
            imageHeightAndWidth[4] = leftMargin;
        } else {
            imagehiegth = 200;
            imagewidth = 200;
            imageHeightAndWidth[0] = imagehiegth;
            imageHeightAndWidth[1] = imagewidth;
        }

        return imageHeightAndWidth;
    }

    public int[] getImageHeightAndWidthForGellary(Activity activity) {
        // //Log.i(TAG, "getImageHeightAndWidth");

        int imageHeightAndWidth[] = new int[2];
        int screenHeight = getHeight(activity);
        int screenWidth = getWidth(activity);
        // //Log.i(TAG, "getImageHeightAndWidth  screenHeight "+screenHeight);
        // //Log.i(TAG, "getImageHeightAndWidth  screenWidth  "+screenWidth);
        int imagehiegth;
        int imagewidth;
        if ((screenHeight <= 500 && screenHeight >= 480)
                && (screenWidth <= 340 && screenWidth >= 300)) {
            // //Log.i(TAG, "getImageHeightAndWidth mdpi");
            imagehiegth = 100;
            imagewidth = 100;
            imageHeightAndWidth[0] = imagehiegth;
            imageHeightAndWidth[1] = imagewidth;

        }

        else if ((screenHeight <= 400 && screenHeight >= 300)
                && (screenWidth <= 240 && screenWidth >= 220))

        {

            // //Log.i(TAG, "getImageHeightAndWidth ldpi");
            imagehiegth = 120;
            imagewidth = 120;
            imageHeightAndWidth[0] = imagehiegth;
            imageHeightAndWidth[1] = imagewidth;
        }

        else if ((screenHeight <= 840 && screenHeight >= 780)
                && (screenWidth <= 500 && screenWidth >= 440)) {

            // //Log.i(TAG, "getImageHeightAndWidth hdpi");
            imagehiegth = 150;
            imagewidth = 150;
            imageHeightAndWidth[0] = imagehiegth;
            imageHeightAndWidth[1] = imagewidth;
        } else if ((screenHeight <= 1280 && screenHeight >= 840)
                && (screenWidth <= 720 && screenWidth >= 500)) {

            // //Log.i(TAG, "getImageHeightAndWidth xdpi");
            imagehiegth = 200;
            imagewidth = 200;
            imageHeightAndWidth[0] = imagehiegth;
            imageHeightAndWidth[1] = imagewidth;
        } else {
            imagehiegth = 250;
            imagewidth = 250;
            imageHeightAndWidth[0] = imagehiegth;
            imageHeightAndWidth[1] = imagewidth;
        }

        return imageHeightAndWidth;
    }

    public int[] getImageHeightAndWidthForProfileGellary(Activity activity) {
        // //Log.i(TAG, "getImageHeightAndWidth");

        int imageHeightAndWidth[] = new int[2];
        int screenHeight = getHeight(activity);
        int screenWidth = getWidth(activity);
        // //Log.i(TAG, "getImageHeightAndWidth  screenHeight "+screenHeight);
        // //Log.i(TAG, "getImageHeightAndWidth  screenWidth  "+screenWidth);
        int imagehiegth;
        int imagewidth;
        if ((screenHeight <= 500 && screenHeight >= 480)
                && (screenWidth <= 340 && screenWidth >= 300)) {
            // //Log.i(TAG, "getImageHeightAndWidth mdpi");
            imagehiegth = 100;
            imagewidth = 100;
            imageHeightAndWidth[0] = imagehiegth;
            imageHeightAndWidth[1] = imagewidth;

        }

        else if ((screenHeight <= 400 && screenHeight >= 300)
                && (screenWidth <= 240 && screenWidth >= 220))

        {

            // //Log.i(TAG, "getImageHeightAndWidth ldpi");
            imagehiegth = 400;
            imagewidth = 400;
            imageHeightAndWidth[0] = imagehiegth;
            imageHeightAndWidth[1] = imagewidth;
        }

        else if ((screenHeight <= 840 && screenHeight >= 780)
                && (screenWidth <= 500 && screenWidth >= 440)) {

            // //Log.i(TAG, "getImageHeightAndWidth hdpi");
            imagehiegth = 500;
            imagewidth = 480;
            imageHeightAndWidth[0] = imagehiegth;
            imageHeightAndWidth[1] = imagewidth;
        } else if ((screenHeight <= 1280 && screenHeight >= 840)
                && (screenWidth <= 720 && screenWidth >= 500)) {

            // //Log.i(TAG, "getImageHeightAndWidth xdpi");
            imagehiegth = 700;
            imagewidth = 720;
            imageHeightAndWidth[0] = imagehiegth;
            imageHeightAndWidth[1] = imagewidth;
        } else {
            imagehiegth = 250;
            imagewidth = 250;
            imageHeightAndWidth[0] = imagehiegth;
            imageHeightAndWidth[1] = imagewidth;
        }

        return imageHeightAndWidth;
    }

    public int[] getImageHeightAndWidthForEditProfileScreen(Activity activity) {
        // //Log.i(TAG, "getImageHeightAndWidth");

        int imageHeightAndWidth[] = new int[2];
        int screenHeight = getHeight(activity);
        int screenWidth = getWidth(activity);
        // //Log.i(TAG, "getImageHeightAndWidth  screenHeight "+screenHeight);
        // //Log.i(TAG, "getImageHeightAndWidth  screenWidth  "+screenWidth);
        int imagehiegth;
        int imagewidth;
        if ((screenHeight <= 500 && screenHeight >= 480)
                && (screenWidth <= 340 && screenWidth >= 300)) {
            // //Log.i(TAG, "getImageHeightAndWidth mdpi");
            imagehiegth = 100;
            imagewidth = 100;
            imageHeightAndWidth[0] = imagehiegth;
            imageHeightAndWidth[1] = imagewidth;

        }

        else if ((screenHeight <= 400 && screenHeight >= 300)
                && (screenWidth <= 240 && screenWidth >= 220))

        {

            // //Log.i(TAG, "getImageHeightAndWidth ldpi");
            imagehiegth = 120;
            imagewidth = 120;
            imageHeightAndWidth[0] = imagehiegth;
            imageHeightAndWidth[1] = imagewidth;
        }

        else if ((screenHeight <= 840 && screenHeight >= 780)
                && (screenWidth <= 500 && screenWidth >= 440)) {

            // //Log.i(TAG, "getImageHeightAndWidth hdpi");
            imagehiegth = 280;
            imagewidth = 280;
            imageHeightAndWidth[0] = imagehiegth;
            imageHeightAndWidth[1] = imagewidth;
        } else if ((screenHeight <= 1280 && screenHeight >= 840)
                && (screenWidth <= 720 && screenWidth >= 500)) {

            // //Log.i(TAG, "getImageHeightAndWidth xdpi");
            imagehiegth = 428;
            imagewidth = 428;
            imageHeightAndWidth[0] = imagehiegth;
            imageHeightAndWidth[1] = imagewidth;
        } else {
            imagehiegth = 200;
            imagewidth = 200;
            imageHeightAndWidth[0] = imagehiegth;
            imageHeightAndWidth[1] = imagewidth;
        }

        return imageHeightAndWidth;
    }

    public int[] getImageHeightAndWidthForEditProfileScreenForOther(
            Activity activity) {
        // //Log.i(TAG, "getImageHeightAndWidth");

        int imageHeightAndWidth[] = new int[2];
        int screenHeight = getHeight(activity);
        int screenWidth = getWidth(activity);
        // //Log.i(TAG, "getImageHeightAndWidth  screenHeight "+screenHeight);
        // //Log.i(TAG, "getImageHeightAndWidth  screenWidth  "+screenWidth);
        int imagehiegth;
        int imagewidth;
        if ((screenHeight <= 500 && screenHeight >= 480)
                && (screenWidth <= 340 && screenWidth >= 300)) {
            // //Log.i(TAG, "getImageHeightAndWidth mdpi");
            imagehiegth = 100;
            imagewidth = 100;
            imageHeightAndWidth[0] = imagehiegth;
            imageHeightAndWidth[1] = imagewidth;

        }

        else if ((screenHeight <= 400 && screenHeight >= 300)
                && (screenWidth <= 240 && screenWidth >= 220))

        {

            // //Log.i(TAG, "getImageHeightAndWidth ldpi");
            imagehiegth = 120;
            imagewidth = 120;
            imageHeightAndWidth[0] = imagehiegth;
            imageHeightAndWidth[1] = imagewidth;
        }

        else if ((screenHeight <= 840 && screenHeight >= 780)
                && (screenWidth <= 500 && screenWidth >= 440)) {

            // //Log.i(TAG, "getImageHeightAndWidth hdpi");
            imagehiegth = 130;
            imagewidth = 130;
            imageHeightAndWidth[0] = imagehiegth;
            imageHeightAndWidth[1] = imagewidth;
        } else if ((screenHeight <= 1280 && screenHeight >= 840)
                && (screenWidth <= 720 && screenWidth >= 500)) {

            // //Log.i(TAG, "getImageHeightAndWidth xdpi");
            imagehiegth = 185;
            imagewidth = 185;
            imageHeightAndWidth[0] = imagehiegth;
            imageHeightAndWidth[1] = imagewidth;
        } else {
            imagehiegth = 100;
            imagewidth = 100;
            imageHeightAndWidth[0] = imagehiegth;
            imageHeightAndWidth[1] = imagewidth;
        }

        return imageHeightAndWidth;
    }

    public int[] getImageHeightAndWidthForAlubumListview(Activity activity) {
        // //Log.i(TAG, "getImageHeightAndWidth");

        int imageHeightAndWidth[] = new int[2];
        int screenHeight = getHeight(activity);
        int screenWidth = getWidth(activity);
        // //Log.i(TAG, "getImageHeightAndWidth  screenHeight "+screenHeight);
        // //Log.i(TAG, "getImageHeightAndWidth  screenWidth  "+screenWidth);
        int imagehiegth;
        int imagewidth;
        if ((screenHeight <= 500 && screenHeight >= 480)
                && (screenWidth <= 340 && screenWidth >= 300)) {
            // //Log.i(TAG, "getImageHeightAndWidth mdpi");
            imagehiegth = 50;
            imagewidth = 50;
            imageHeightAndWidth[0] = imagehiegth;
            imageHeightAndWidth[1] = imagewidth;

        }

        else if ((screenHeight <= 400 && screenHeight >= 300)
                && (screenWidth <= 240 && screenWidth >= 220))

        {

            // //Log.i(TAG, "getImageHeightAndWidth ldpi");
            imagehiegth = 50;
            imagewidth = 50;
            imageHeightAndWidth[0] = imagehiegth;
            imageHeightAndWidth[1] = imagewidth;
        }

        else if ((screenHeight <= 840 && screenHeight >= 780)
                && (screenWidth <= 500 && screenWidth >= 440)) {

            // //Log.i(TAG, "getImageHeightAndWidth hdpi");
            imagehiegth = 50;
            imagewidth = 50;
            imageHeightAndWidth[0] = imagehiegth;
            imageHeightAndWidth[1] = imagewidth;
        } else if ((screenHeight <= 1280 && screenHeight >= 840)
                && (screenWidth <= 720 && screenWidth >= 500)) {

            // //Log.i(TAG, "getImageHeightAndWidth xdpi");
            imagehiegth = 150;
            imagewidth = 150;
            imageHeightAndWidth[0] = imagehiegth;
            imageHeightAndWidth[1] = imagewidth;
        } else {
            imagehiegth = 100;
            imagewidth = 100;
            imageHeightAndWidth[0] = imagehiegth;
            imageHeightAndWidth[1] = imagewidth;
        }

        return imageHeightAndWidth;
    }

    // / interest and userfriend image height and width

    public int[] getImageHeightAndWidthForInrestAndFriendsLyout(
            Activity activity) {
        // //Log.i(TAG, "getImageHeightAndWidth");
        int imageHeightAndWidth[] = new int[2];
        int screenHeight = 0;
        int screenWidth = 0;

        try {
            screenHeight = getHeight(activity);
            screenWidth = getWidth(activity);
        } catch (Exception e) {
            logError("getImageHeightAndWidthForInrestAndFriendsLyout Exception "
                    + e);
        }

        // //Log.i(TAG, "getImageHeightAndWidth  screenHeight "+screenHeight);
        // //Log.i(TAG, "getImageHeightAndWidth  screenWidth  "+screenWidth);
        int imagehiegth;
        int imagewidth;
        if ((screenHeight <= 500 && screenHeight >= 480)
                && (screenWidth <= 340 && screenWidth >= 300)) {
            // //Log.i(TAG, "getImageHeightAndWidth mdpi");
            imagehiegth = 50;
            imagewidth = 50;
            imageHeightAndWidth[0] = imagehiegth;
            imageHeightAndWidth[1] = imagewidth;

        }

        else if ((screenHeight <= 400 && screenHeight >= 300)
                && (screenWidth <= 240 && screenWidth >= 220))

        {

            // //Log.i(TAG, "getImageHeightAndWidth ldpi");
            imagehiegth = 50;
            imagewidth = 50;
            imageHeightAndWidth[0] = imagehiegth;
            imageHeightAndWidth[1] = imagewidth;
        }

        else if ((screenHeight <= 840 && screenHeight >= 780)
                && (screenWidth <= 500 && screenWidth >= 440)) {

            // //Log.i(TAG, "getImageHeightAndWidth hdpi");
            imagehiegth = 190;
            imagewidth = 190;
            imageHeightAndWidth[0] = imagehiegth;
            imageHeightAndWidth[1] = imagewidth;
        } else if ((screenHeight <= 1280 && screenHeight >= 840)
                && (screenWidth <= 720 && screenWidth >= 500)) {

            // //Log.i(TAG, "getImageHeightAndWidth xdpi");
            imagehiegth = 220;
            imagewidth = 220;
            imageHeightAndWidth[0] = imagehiegth;
            imageHeightAndWidth[1] = imagewidth;
        } else {
            imagehiegth = 200;
            imagewidth = 200;
            imageHeightAndWidth[0] = imagehiegth;
            imageHeightAndWidth[1] = imagewidth;
        }

        return imageHeightAndWidth;
    }

    public int[] getImageHeightAndWidthForAlubumGridView(Activity activity) {
        // //Log.i(TAG, "getImageHeightAndWidth");

        int imageHeightAndWidth[] = new int[2];
        int screenHeight = getHeight(activity);
        int screenWidth = getWidth(activity);
        // //Log.i(TAG, "getImageHeightAndWidth  screenHeight "+screenHeight);
        // //Log.i(TAG, "getImageHeightAndWidth  screenWidth  "+screenWidth);
        int imagehiegth;
        int imagewidth;
        if ((screenHeight <= 500 && screenHeight >= 480)
                && (screenWidth <= 340 && screenWidth >= 300)) {
            // //Log.i(TAG, "getImageHeightAndWidth mdpi");
            imagehiegth = 50;
            imagewidth = 50;
            imageHeightAndWidth[0] = imagehiegth;
            imageHeightAndWidth[1] = imagewidth;

        }

        else if ((screenHeight <= 400 && screenHeight >= 300)
                && (screenWidth <= 240 && screenWidth >= 220))

        {

            // //Log.i(TAG, "getImageHeightAndWidth ldpi");
            imagehiegth = 100;
            imagewidth = 100;
            imageHeightAndWidth[0] = imagehiegth;
            imageHeightAndWidth[1] = imagewidth;
        }

        else if ((screenHeight <= 840 && screenHeight >= 780)
                && (screenWidth <= 500 && screenWidth >= 440)) {

            // //Log.i(TAG, "getImageHeightAndWidth hdpi");
            imagehiegth = 145;
            imagewidth = 145;
            imageHeightAndWidth[0] = imagehiegth;
            imageHeightAndWidth[1] = imagewidth;
        } else if ((screenHeight <= 1280 && screenHeight >= 840)
                && (screenWidth <= 720 && screenWidth >= 500)) {

            // //Log.i(TAG, "getImageHeightAndWidth xdpi");
            imagehiegth = 215;
            imagewidth = 215;
            imageHeightAndWidth[0] = imagehiegth;
            imageHeightAndWidth[1] = imagewidth;
        } else {
            imagehiegth = 250;
            imagewidth = 250;
            imageHeightAndWidth[0] = imagehiegth;
            imageHeightAndWidth[1] = imagewidth;
        }

        return imageHeightAndWidth;
    }

    public int[] getImageHeightForChatScreen(Activity activity) {
        // //Log.i(TAG, "getImageHeightAndWidth");

        int imageHeightAndWidth[] = new int[2];
        int screenHeight = getHeight(activity);
        int screenWidth = getWidth(activity);
        // //Log.i(TAG, "getImageHeightAndWidth  screenHeight "+screenHeight);
        // //Log.i(TAG, "getImageHeightAndWidth  screenWidth  "+screenWidth);
        int imagehiegth;
        int imagewidth;
        if ((screenHeight <= 500 && screenHeight >= 480)
                && (screenWidth <= 340 && screenWidth >= 300)) {
            // //Log.i(TAG, "getImageHeightAndWidth mdpi");
            imagehiegth = 50;
            imagewidth = 50;
            imageHeightAndWidth[0] = imagehiegth;
            imageHeightAndWidth[1] = imagewidth;

        }

        else if ((screenHeight <= 400 && screenHeight >= 300)
                && (screenWidth <= 240 && screenWidth >= 220))

        {

            // //Log.i(TAG, "getImageHeightAndWidth ldpi");
            imagehiegth = 100;
            imagewidth = 100;
            imageHeightAndWidth[0] = imagehiegth;
            imageHeightAndWidth[1] = imagewidth;
        }

        else if ((screenHeight <= 840 && screenHeight >= 780)
                && (screenWidth <= 500 && screenWidth >= 440)) {

            // //Log.i(TAG, "getImageHeightAndWidth hdpi");
            imagehiegth = 200;
            imagewidth = 200;
            imageHeightAndWidth[0] = imagehiegth;
            imageHeightAndWidth[1] = imagewidth;
        } else if ((screenHeight <= 1280 && screenHeight >= 840)
                && (screenWidth <= 720 && screenWidth >= 500)) {

            // //Log.i(TAG, "getImageHeightAndWidth xdpi");
            imagehiegth = 220;
            imagewidth = 220;
            imageHeightAndWidth[0] = imagehiegth;
            imageHeightAndWidth[1] = imagewidth;
        } else {
            imagehiegth = 250;
            imagewidth = 250;
            imageHeightAndWidth[0] = imagehiegth;
            imageHeightAndWidth[1] = imagewidth;
        }

        return imageHeightAndWidth;
    }

    public int getDeviceType(Activity activity) {
        // //Log.i(TAG, "getImageHeightAndWidth");

        int devieytype = 0;
        int screenHeight = getHeight(activity);
        int screenWidth = getWidth(activity);
        // //Log.i(TAG, "getImageHeightAndWidth  screenHeight "+screenHeight);
        // //Log.i(TAG, "getImageHeightAndWidth  screenWidth  "+screenWidth);
        // int imagehiegth;
        // int imagewidth;
        if ((screenHeight <= 500 && screenHeight >= 480)
                && (screenWidth <= 340 && screenWidth >= 300)) {
            // //Log.i(TAG, "getImageHeightAndWidth mdpi");
            devieytype = 1;

        }

        else if ((screenHeight <= 400 && screenHeight >= 300)
                && (screenWidth <= 240 && screenWidth >= 220))

        {

            // //Log.i(TAG, "getImageHeightAndWidth ldpi");
            devieytype = 1;

        }

        else if ((screenHeight <= 840 && screenHeight >= 780)
                && (screenWidth <= 500 && screenWidth >= 440)) {

            // //Log.i(TAG, "getImageHeightAndWidth hdpi");
            devieytype = 1;

        } else if ((screenHeight <= 1280 && screenHeight >= 840)
                && (screenWidth <= 720 && screenWidth >= 500)) {

            // //Log.i(TAG, "getImageHeightAndWidth xdpi");

            devieytype = 1;
        } else {
            devieytype = 3;
        }

        return devieytype;
    }


    public void displayMessageAndExit(Activity activity, String tiltleMassage,
                                      String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(tiltleMassage);
        builder.setMessage(message);
        builder.setPositiveButton("Ok", new FinishListener(activity));
        builder.setOnCancelListener(new FinishListener(activity));
        builder.setCancelable(false);
        builder.show();
    }




    public static String getColor() {
        return color;
    }


    public static void setColor(String color) {
        Utilities.color = color;
    }









    public RelativeLayout.LayoutParams getRelativelayoutParams(int width,
                                                               int height) {
        RelativeLayout.LayoutParams lp = null;

        lp = new RelativeLayout.LayoutParams(/*
											 * RelativeLayout.LayoutParams.
											 * WRAP_CONTENT
											 */width, /*
													 * RelativeLayout.LayoutParams
													 * .WRAP_CONTENT
													 */height);

        return lp;
    }

}
