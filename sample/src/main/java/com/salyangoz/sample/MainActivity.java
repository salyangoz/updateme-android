package com.salyangoz.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.salyangoz.updateme.UpdateMe;
import com.salyangoz.updateme.listener.OnNegativeButtonClickListener;
import com.salyangoz.updateme.listener.OnPositiveButtonClickListener;
import com.yarolegovich.lovelydialog.LovelyStandardDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UpdateMe.with(this, 30).setDialogVisibility(true)
                .continueButtonVisibility(true)
                .setPositiveButtonText("MF Go!")
                .setNegativeButtonText("Oh No...")
                .setDialogIcon(R.drawable.common_google_signin_btn_icon_dark)
                .onNegativeButtonClick(new OnNegativeButtonClickListener() {

                    @Override
                    public void onClick(LovelyStandardDialog dialog) {

                        Log.d(UpdateMe.TAG, "Later Button Clicked");
                        dialog.dismiss();
                    }
                })
                .onPositiveButtonClick(new OnPositiveButtonClickListener() {

                    @Override
                    public void onClick(LovelyStandardDialog dialog) {

                        Log.d(UpdateMe.TAG, "Update Button Clicked");
                        dialog.dismiss();
                    }
                })
                .check();

    }
}
