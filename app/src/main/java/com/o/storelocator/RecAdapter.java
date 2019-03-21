package com.o.storelocator;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.ViewHolder> {

    List<item> itemList;

    public RecAdapter( List<item> itemList){
        this.itemList = itemList;
    }

    public RecAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_list_item, null);

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecAdapter.ViewHolder viewHolder, int i) {

    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView name;
        public TextView price;

        public ViewHolder(View itemLayoutView){
            super(itemLayoutView);

            name = (TextView) itemLayoutView.findViewById(R.id.iName);
            price = (TextView) itemLayoutView.findViewById(R.id.iPrice);
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

}
