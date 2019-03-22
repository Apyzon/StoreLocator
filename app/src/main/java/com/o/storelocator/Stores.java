package com.o.storelocator;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
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

    String storeID;

    String[] itemNames = {"Fanta", "Walkers, Prawn Cocktail", "Coke Zero", "Ham Sandwich", "Pepsi",
            "Conditioner","Gum, Spearmint", "Doritos, Heat", "Tango, apple", "Squares", "7UP", "Lighter"};

    Double[] itemPrices = {1.10,0.60,1.20,1.80,1.20,4.50,0.30,1.00,1.20,1.00,1.20,0.50};

    int[] images = {R.drawable.fanta,R.drawable.walkers_prawn,R.drawable.coke_zero,R.drawable.ham_sandwich,
            R.drawable.pepsi,R.drawable.conditioner,R.drawable.gum,R.drawable.doritos,R.drawable.tango,
            R.drawable.squares,R.drawable.up,R.drawable.lighter};

    ListView lView;

    ListAdapter lAdapter;


    public Stores() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_stores, container, false);

        lView = (ListView) v.findViewById(R.id.item_list);

//        getItemNames();
//        getItemPrices();

        lAdapter = new ListAdapter(this.getActivity(), itemNames, itemPrices, images);

        lView.setAdapter(lAdapter);
        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                db.collection("Items")
                        .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()){
                            List<DocumentSnapshot> ds = queryDocumentSnapshots.getDocuments();

                            for (DocumentSnapshot d : ds){
                                if(itemNames[position].equals(d.getData().get("Name").toString())){
                                    storeID = d.getData().get("StoreID").toString();
                                }
                            }
                        }
                    }
                });

                Toast.makeText(getActivity(),"Item: " + itemNames[position] + " StoreID: " + storeID,Toast.LENGTH_LONG).show();
            }
        });

        // Inflate the layout for this fragment
        return v;
    }

//    public void getItemNames(){
//        db.collection("Items")
//                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//            @Override
//            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                if (!queryDocumentSnapshots.isEmpty()){
//                    List<DocumentSnapshot> ds = queryDocumentSnapshots.getDocuments();
//
//                    for (DocumentSnapshot d : ds){
//                        name = (String) d.getData().get("Name");
//                        Toast.makeText(getActivity(),name,Toast.LENGTH_LONG).show();
//                        itemNames.add(name);
//                    }
//                }
//            }
//        });
//    }
//
//    public void getItemPrices(){
//        db.collection("Items")
//                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//            @Override
//            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                if (!queryDocumentSnapshots.isEmpty()){
//                    List<DocumentSnapshot> ds = queryDocumentSnapshots.getDocuments();
//
//                    for (DocumentSnapshot d : ds){
//                        price = (Double) d.getLong("Price").doubleValue();
//                        Toast.makeText(getActivity(),price.toString(),Toast.LENGTH_LONG).show();
//                        itemPrices.add(price);
//                    }
//                }
//            }
//        });
//    }
}
