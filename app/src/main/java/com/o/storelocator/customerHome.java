package com.o.storelocator;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class customerHome extends AppCompatActivity {

    private TextView mTextMessage;
    FragmentTransaction transaction;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    String name;

    Double price;

    List<String> itemNames = new ArrayList<>();

    List<Double> itemPrices = new ArrayList<>();

    ListView lView;

    ListAdapter lAdapter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_map:
                    //mTextMessage.setText(R.string.cus_title_1);
                    transaction = getSupportFragmentManager().beginTransaction();
                    transaction.add(R.id.frameLayout, new Map());
                    transaction.commit();
                    return true;
                case R.id.navigation_stores:
                    //mTextMessage.setText(R.string.cus_title_2);
                    transaction = getSupportFragmentManager().beginTransaction();
                    transaction.add(R.id.frameLayout, new Stores());
                    transaction.commit();
                    return true;
                case R.id.navigation_search:
                    //mTextMessage.setText(R.string.cus_title_3);
                    transaction = getSupportFragmentManager().beginTransaction();
                    transaction.add(R.id.frameLayout, new Search());
                    transaction.commit();
                    return true;
                case R.id.navigation_lists:
                    //mTextMessage.setText(R.string.cus_title_3);
                    transaction = getSupportFragmentManager().beginTransaction();
                    transaction.add(R.id.frameLayout, new Lists());
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

    public void getItemNames(){
        db.collection("Items")
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()){
                    List<DocumentSnapshot> ds = queryDocumentSnapshots.getDocuments();

                    for (DocumentSnapshot d : ds){
                        name = (String) d.getData().get("Name");
                        Toast.makeText(getBaseContext(),name,Toast.LENGTH_LONG).show();
                        itemNames.add(name);
                    }
                }
            }
        });
    }

    public void getItemPrices(){
        db.collection("Items")
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()){
                    List<DocumentSnapshot> ds = queryDocumentSnapshots.getDocuments();

                    for (DocumentSnapshot d : ds){
                        price = (Double) d.getLong("Price").doubleValue();
                        Toast.makeText(getBaseContext(),price.toString(),Toast.LENGTH_LONG).show();
                        itemPrices.add(price);
                    }
                }
            }
        });
    }
}


