package com.o.storelocator;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Stores extends Fragment {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    String name;

    Double price;

    List<String> itemNames = new ArrayList<>();

    List<Double> itemPrices = new ArrayList<>();

    ListView lView;

    ListAdapter lAdapter;


    public Stores() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_stores, container, false);

        lView = (ListView) v.findViewById(R.id.item_list1);

        getItemNames();
        getItemPrices();

        lAdapter = new ListAdapter(this.getActivity(), itemNames, itemPrices);

        lView.setAdapter(lAdapter);

        // Inflate the layout for this fragment
        return v;
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
                        Toast.makeText(getActivity(),name,Toast.LENGTH_LONG).show();
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
                        Toast.makeText(getActivity(),price.toString(),Toast.LENGTH_LONG).show();
                        itemPrices.add(price);
                    }
                }
            }
        });
    }
}
