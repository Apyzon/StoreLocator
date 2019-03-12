package com.o.storelocator;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

//import android.app.Fragment;

public class customerHome extends AppCompatActivity {

    private TextView mTextMessage;
    FragmentTransaction transaction;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.cus_title_1);
                    transaction = getSupportFragmentManager().beginTransaction();
                    transaction.add(R.id.frameLayout, new Map());
                    transaction.commit();
                    return true;
                case R.id.navigation_dashboard:
                    //mTextMessage.setText(R.string.cus_title_2);
                    transaction = getSupportFragmentManager().beginTransaction();
                    transaction.add(R.id.frameLayout, new Stores());
                    transaction.commit();
                    return true;
                case R.id.navigation_notifications:
                    //mTextMessage.setText(R.string.cus_title_3);
                    transaction = getSupportFragmentManager().beginTransaction();
                    transaction.add(R.id.frameLayout, new Search());
                    transaction.commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_home);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
}


