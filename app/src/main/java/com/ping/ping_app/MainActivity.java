package com.ping.ping_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_me:
                    mTextMessage.setText(R.string.title_country);
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

        mTextMessage = (TextView) findViewById(R.id.message);

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
