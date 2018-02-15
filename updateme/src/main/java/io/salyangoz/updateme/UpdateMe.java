package io.salyangoz.updateme;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import io.salyangoz.updateme.listener.OnNegativeButtonClickListener;
import io.salyangoz.updateme.listener.OnPositiveButtonClickListener;
import io.salyangoz.updateme.listener.OnUpdateNeededListener;
import io.salyangoz.updateme.model.Config;
import io.salyangoz.updateme.model.Settings;
import io.salyangoz.updateme.util.Utilities;
import com.yarolegovich.lovelydialog.LovelyStandardDialog;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Salyangoz.Co on 29/07/2017.
 */

public class UpdateMe implements OnCompleteListener {

    private static final String DEFAULT_UPDATE_URL = "https://play.google.com/store/apps/details?id=com.google.android.googlequicksearchbox";
    private OnPositiveButtonClickListener onPositiveButtonClickListener;
    private OnNegativeButtonClickListener onNegativeButtonClickListener;
    public static final String TAG = UpdateMe.class.getSimpleName();
    private static final String DEFAULT_CURRENT_VERSION = "1.0.0";
    private static final Boolean DEFAULT_UPDATE_NEEDED = false;
    private OnUpdateNeededListener onUpdateNeededListener;
    private FirebaseRemoteConfig firebaseRemoteConfig;
    private int checkIntervalInSeconds;
    private int positiveButtonColorRes;
    private int negativeButtonColorRes;
    private String positiveButtonText;
    private String negativeButtonText;
    private Boolean dialogVisibility;
    private Boolean continueUsingApp;
    private int topColorRes;
    private Context context;
    private int dialogIcon;

    public static UpdateMeBuilder with(@NonNull Context context) {

        return new UpdateMeBuilder(context);
    }

    public static UpdateMeBuilder with(@NonNull Context context, @NonNull int fetchIntervalInSeconds) {

        return new UpdateMeBuilder(context).setFetchInterval(fetchIntervalInSeconds);
    }

    public UpdateMe(@NonNull Context context, OnUpdateNeededListener onUpdateNeededListener, OnNegativeButtonClickListener onNegativeButtonClickListener, OnPositiveButtonClickListener onPositiveButtonClickListener, int checkIntervalInSeconds, String positiveButtonText, String negativeButtonText, Boolean dialogVisibility, Boolean continueButtonVisibility, int dialogIcon, int positiveButtonColorRes, int negativeButtonColorRes, int topColorRes) {

        this.context = context;
        this.onUpdateNeededListener = onUpdateNeededListener;
        this.onPositiveButtonClickListener = onPositiveButtonClickListener;
        this.onNegativeButtonClickListener = onNegativeButtonClickListener;
        this.checkIntervalInSeconds = checkIntervalInSeconds;
        this.positiveButtonText = positiveButtonText;
        this.negativeButtonText = negativeButtonText;
        this.dialogVisibility = dialogVisibility;
        this.continueUsingApp = continueButtonVisibility;
        this.dialogIcon = dialogIcon;
        this.positiveButtonColorRes = positiveButtonColorRes;
        this.negativeButtonColorRes = negativeButtonColorRes;
        this.topColorRes = topColorRes;

        init();
    }

    private void init() {

        FirebaseApp.initializeApp(context);

        firebaseRemoteConfig = FirebaseRemoteConfig.getInstance();

        //Default Configurations
        Map<String, Object> remoteConfigDefaults = new HashMap<>();
        remoteConfigDefaults.put(Config.KEY_UPDATE_REQUIRED, DEFAULT_UPDATE_NEEDED);
        remoteConfigDefaults.put(Config.KEY_CURRENT_VERSION, DEFAULT_CURRENT_VERSION);
        remoteConfigDefaults.put(Config.KEY_UPDATE_URL, DEFAULT_UPDATE_URL);
        remoteConfigDefaults.put(Config.KEY_DIALOG_DESCRIPTION, context.getResources().getString(R.string.dialog_message));
        remoteConfigDefaults.put(Config.KEY_DIALOG_TITLE, context.getResources().getString(R.string.dialog_title));


        firebaseRemoteConfig.setDefaults(remoteConfigDefaults);
        firebaseRemoteConfig.fetch(checkIntervalInSeconds).addOnCompleteListener(this);
    }

    public void check() {

        final FirebaseRemoteConfig remoteConfig = FirebaseRemoteConfig.getInstance();

        //Check for update need
        Boolean updateNeed = remoteConfig.getBoolean(Config.KEY_UPDATE_REQUIRED);

        if (updateNeed) {

            //Get remote config data
            final String message = remoteConfig.getString(Config.KEY_DIALOG_DESCRIPTION).isEmpty() ? context.getResources().getString(R.string.dialog_message) : remoteConfig.getString(Config.KEY_DIALOG_DESCRIPTION);
            final String title = remoteConfig.getString(Config.KEY_DIALOG_TITLE).isEmpty() ? context.getResources().getString(R.string.dialog_title) : remoteConfig.getString(Config.KEY_DIALOG_TITLE);
            final String currentVersion = remoteConfig.getString(Config.KEY_CURRENT_VERSION);
            final String updateUrl = remoteConfig.getString(Config.KEY_UPDATE_URL);
            final String positiveButtonText = this.positiveButtonText;
            final String appVersion = Utilities.getAppVersion(context);

            Settings settings = new Settings(title, message, updateUrl,(updateNeed && (!TextUtils.equals(appVersion, currentVersion))));
            if (onUpdateNeededListener != null)
                onUpdateNeededListener.onUpdateNeeded(settings);

            //If version is different from the current one call listener to show dialog
            if ((!TextUtils.equals(appVersion, currentVersion))) {

                if (!dialogVisibility)
                    return;

                //To Do Set Colors Dynamic and Negative Button Configurable
                final LovelyStandardDialog dialog =
                        new LovelyStandardDialog(context)
                                .setIcon(dialogIcon)
                                .setNegativeButtonColorRes(negativeButtonColorRes)
                                .setPositiveButtonColorRes(positiveButtonColorRes)
                                .setTopColorRes(topColorRes)
                                .setCancelable(false)
                                .setMessage(message)
                                .setTitle(title);

                //Dialog Positive Button On Click
                dialog.setPositiveButton(positiveButtonText, new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        onPositiveButtonClickListener.onClick(dialog);
                        Utilities.openStore(context, updateUrl);
                    }
                });

                //Dialog Negative Button On Click
                if (continueUsingApp) {
                    dialog.setNegativeButton(negativeButtonText, new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {

                            onNegativeButtonClickListener.onClick(dialog);
                        }
                    });
                }
                dialog.show();

            }

        }

    }

    @Override
    public void onComplete(@NonNull Task task) {

        if (task.isSuccessful()) {
            Log.d(UpdateMe.TAG, "remote config is fetched.");
            firebaseRemoteConfig.activateFetched();
        }

    }

}
