package com.ping.ping_app;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Toast mToast;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_me:
//                    ActivityManager activityManager=(ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
//                    String runningActivity=activityManager.getRunningTasks(1).get(0).topActivity.getClassName();
//                    String ActivityName = runningActivity.substring(runningActivity.length()-12,runningActivity.length());
////                    mToast = Toast.makeText(getApplicationContext(),ActivityName, Toast.LENGTH_LONG);
////                    mToast.show();
//                    if(!ActivityName.equals("MainActivity"))
//                    toMainActivity();
                    return true;
                case R.id.navigation_country:
                    //toShowCountryActivity();
                    toContryListActivity();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //mTextMessage = (TextView) findViewById(R.id.message);

    }

    void toMainActivity (){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    void toShowCountryActivity (){
        Intent intent = new Intent(this, ShowCountryActivity.class);
        startActivity(intent);
    }

    void toContryListActivity (){
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }
}
