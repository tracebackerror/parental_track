
package app.qk.teliver.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.teliver.sdk.models.TConstants;

import app.qk.teliver.R;


public class Utils {


    public static void setUpToolBar(final Activity context, Toolbar toolbar,
                                    ActionBar actionBar, String title) {
        try {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setTitle(title);
            toolbar.getNavigationIcon().setColorFilter(
                    ContextCompat.getColor(context, android.R.color.white),
                    PorterDuff.Mode.SRC_IN);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.finish();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isNetConnected(Context context) {
        ConnectivityManager conMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        return conMgr.getActiveNetworkInfo() != null
                && conMgr.getActiveNetworkInfo().isAvailable()
                && conMgr.getActiveNetworkInfo().isConnected();
    }


    public void init(TextView txtView, Context context, AttributeSet attrs) {
        try {
            Typeface typeface = getCustomFont(context, attrs);
            if (typeface != null)
                txtView.setTypeface(typeface);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Typeface getCustomFont(Context context, AttributeSet attrs) {
        Typeface typeface = null;
        try {
            TypedArray typedArray = context.obtainStyledAttributes(attrs,
                    R.styleable.CustomWidget);
            for (int i = 0, count = typedArray.getIndexCount(); i < count; i++) {
                int attribute = typedArray.getIndex(i);
                if (attribute == R.styleable.CustomWidget_font_name) {
                    typeface = Typeface.createFromAsset(context.getResources()
                            .getAssets(), typedArray.getString(attribute));
                }
            }
            typedArray.recycle();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return typeface;
    }


    public static void showLocationAlert(final Context context) {
        try {
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(context, R.style.MyAlertDialogStyle);
            mBuilder.setMessage(context.getString(R.string.text_location_disabled));
            mBuilder.setPositiveButton(context.getString(R.string.txt_ok),
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            Intent myIntent = new Intent(
                                    Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            context.startActivity(myIntent);
                        }
                    });
            mBuilder.setNegativeButton(context.getString(R.string.txt_cancel),
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            AlertDialog dialog = mBuilder.create();
            dialog.setCancelable(false);
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showSnack(View view, String msg) {
        Snackbar.make(view, msg, 2000).show();
    }

    public static boolean checkPermission(Activity activity) {
        String permission = Manifest.permission.ACCESS_FINE_LOCATION;
        if (ContextCompat.checkSelfPermission(activity, permission)
                == PackageManager.PERMISSION_GRANTED)
            return true;
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                permission))
            ActivityCompat.requestPermissions(activity,
                    new String[]{permission}, TConstants.PERMISSION_REQ_CODE);
        else
            ActivityCompat.requestPermissions(activity,
                    new String[]{permission}, TConstants.PERMISSION_REQ_CODE);
        return false;
    }
}
