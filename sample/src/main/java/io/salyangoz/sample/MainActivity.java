package io.salyangoz.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.salyangoz.updateme.UpdateMe;
import io.salyangoz.updateme.listener.OnNegativeButtonClickListener;
import io.salyangoz.updateme.listener.OnPositiveButtonClickListener;
import com.yarolegovich.lovelydialog.LovelyStandardDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UpdateMe.with(this).check();

        UpdateMe.with(this, 30).setDialogVisibility(true)
                .continueButtonVisibility(true)
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
