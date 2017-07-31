package com.salyangoz.updateme.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;

import com.salyangoz.updateme.UpdateMe;

/**
 * Created by Salyangoz.Co on 29/07/2017.
 */

public class Utilities {

    public static String getAppVersion(Context context) {

        String result = "";

        try {
            result = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0)
                    .versionName;
            result = result.replaceAll("[a-zA-Z]|-", "");
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(UpdateMe.TAG, e.getMessage());
        }

        return result;
    }

    public static void openStore(Context context, String updateUrl) {

        final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(updateUrl));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
