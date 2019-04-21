package app.qk.teliver.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.teliver.sdk.core.Teliver;

import app.qk.teliver.R;
import app.qk.teliver.utils.Constants;
import app.qk.teliver.utils.MPreference;

public class ActivityLauncher extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        final MPreference preference = new MPreference(this);

        /*
        Teliver.stopTrip("5cb5f1ab2c903e0005a5b800");
        Teliver.stopTrip("bhaleraoy299@gmail.com");

        Teliver.stopTracking("5cb5f1ab2c903e0005a5b800");
        Teliver.stopTracking("bhaleraoy299@gmail.com");

        Teliver.stopTrip("5cb5f5582c903e0005a5b81b");
        Teliver.stopTrip("soorajamishra@gmail.com");

        Teliver.stopTracking("5cb5f5582c903e0005a5b81b");
        Teliver.stopTracking("soorajamishra@gmail.com");


        Teliver.stopTrip("5cb5f2782c903e0005a5b805");
        Teliver.stopTracking("5cb5f2782c903e0005a5b805");
        Teliver.stopTrip("bhalerao299@gmail.com");
        Teliver.stopTracking("bhalerao299@gmail.com");
    */
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_user);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                Intent intent;
                if (i == R.id.radio_customer) {
                    intent = new Intent(ActivityLauncher.this, ActivityCustomer.class);
                    preference.storeBoolean(Constants.LOGGED_IN_CUSTOMER, true);
                } else {
                    intent = new Intent(ActivityLauncher.this, ActivityDriver.class);
                    preference.storeBoolean(Constants.LOGGED_IN_CUSTOMER, false);
                }
                preference.storeBoolean(Constants.IS_LOGGED_IN, true);
                startActivity(intent);
            }
        });
    }
}
