package com.test.doc.atostecheval;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String AndroidID = "AndroidID";
    private String IMEI = "IMEI";
    private Context context;
    private TelephonyManager telephonyManager;
    private int apiLevel;

    private TextView textViewIMEI;
    private TextView textViewAndroidID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewAndroidID = (TextView) findViewById(R.id.AndroidID_value);
        textViewIMEI = (TextView) findViewById(R.id.IMEI_value);

        apiLevel = Build.VERSION.SDK_INT;
        context = getApplicationContext();
        telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        if(ContextCompat.checkSelfPermission(this ,Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED){
            if(apiLevel < 26) {
                IMEI = telephonyManager.getDeviceId();
            } else {
                IMEI = telephonyManager.getImei();
            }
            textViewIMEI.setText(IMEI);
        } else {
            textViewIMEI.setText("Permission denied");
        }

        AndroidID = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        textViewAndroidID.setText(AndroidID);


    }

}
