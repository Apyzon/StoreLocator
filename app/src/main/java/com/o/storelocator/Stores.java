package com.o.storelocator;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.Constraints.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class Stores extends Fragment {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    List<String> itemNames = getItemNames();

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

        lView = (ListView) v.findViewById(R.id.item_list);

        lAdapter = new ListAdapter(getContext(), itemNames , itemPrices);

        lView.setAdapter(lAdapter);

        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(getActivity(), itemNames.get(i)+" "+itemPrices.get(i), Toast.LENGTH_SHORT).show();

            }
        });

        // Inflate the layout for this fragment
        return v;
    }

    public List<String> getItemNames(){
        DocumentReference docRef = db.collection("Items").document("1");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        itemNames.add(document.getString("Name"));
                        Toast.makeText(getActivity(),itemNames.get(0),Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }

            }
        });
        return itemNames;
    }

}
