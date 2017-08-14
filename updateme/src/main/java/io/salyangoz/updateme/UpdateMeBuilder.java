package io.salyangoz.updateme;

import io.salyangoz.updateme.listener.OnNegativeButtonClickListener;
import io.salyangoz.updateme.listener.OnPositiveButtonClickListener;
import io.salyangoz.updateme.listener.OnUpdateNeededListener;

import android.content.Context;


/**
 * Created by Salyangoz.Co on 29/07/2017.
 */

public class UpdateMeBuilder {

    private OnUpdateNeededListener onUpdateNeededListener;
    private OnPositiveButtonClickListener onPositiveButtonClickListener;
    private OnNegativeButtonClickListener onNegativeButtonClickListener;
    private int checkIntervalInSeconds = 30;
    private Boolean dialogVisibility = true;
    private Boolean continueButtonVisibility = false;
    private String positiveButtonText = "";
    private String negativeButtonText = "";
    private int positiveButtonColorRes;
    private int negativeButtonColorRes;
    private int topColorRes;
    private Context context;
    private int dialogIcon;


    public UpdateMeBuilder(Context context) {

        this.context = context;
        this.positiveButtonText = context.getResources().getString(R.string.dialog_positive_button_title);
        this.negativeButtonText = context.getResources().getString(R.string.dialog_negative_button_title);
        this.dialogIcon = R.drawable.ic_info_outline_white_24dp;
        this.positiveButtonColorRes = android.R.color.holo_orange_dark;
        this.negativeButtonColorRes = android.R.color.darker_gray;
        this.topColorRes = android.R.color.holo_orange_light;

    }

    public UpdateMeBuilder setFetchInterval(int seconds) {

        this.checkIntervalInSeconds = seconds;
        return this;
    }

    public UpdateMeBuilder onUpdateNeeded(OnUpdateNeededListener onUpdateNeededListener) {

        this.onUpdateNeededListener = onUpdateNeededListener;
        return this;
    }

    public UpdateMeBuilder onPositiveButtonClick(OnPositiveButtonClickListener onPositiveButtonClickListener) {

        this.onPositiveButtonClickListener = onPositiveButtonClickListener;
        return this;
    }

    public UpdateMeBuilder onNegativeButtonClick(OnNegativeButtonClickListener onNegativeButtonClickListener) {

        this.onNegativeButtonClickListener = onNegativeButtonClickListener;
        return this;
    }

    public UpdateMe build() {

        return new UpdateMe(context,
                onUpdateNeededListener,
                onNegativeButtonClickListener,
                onPositiveButtonClickListener,
                checkIntervalInSeconds,
                positiveButtonText,
                negativeButtonText,
                dialogVisibility,
                continueButtonVisibility,
                dialogIcon,
                positiveButtonColorRes,
                negativeButtonColorRes,
                topColorRes);

    }

    public UpdateMe check() {

        UpdateMe updateMe = build();
        updateMe.check();

        return updateMe;

    }

    public UpdateMeBuilder setDialogVisibility(Boolean visibility) {

        this.dialogVisibility = visibility;
        return this;
    }

    public UpdateMeBuilder setPositiveButtonText(String buttonText) {

        this.positiveButtonText = buttonText;
        return this;
    }

    public UpdateMeBuilder setNegativeButtonText(String buttonText) {

        this.negativeButtonText = buttonText;
        return this;
    }

    public UpdateMeBuilder continueButtonVisibility(Boolean visibility) {

        this.continueButtonVisibility = visibility;
        return this;
    }

    public UpdateMeBuilder setDialogIcon(int icon) {

        this.dialogIcon = icon;
        return this;
    }

    public UpdateMeBuilder setPositiveButtonColorRes(int color) {

        this.positiveButtonColorRes = color;
        return this;
    }

    public UpdateMeBuilder setNegativeButtonColorRes(int icon) {

        this.negativeButtonColorRes = icon;
        return this;
    }

}