package com.o.storelocator;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class ListAdapter extends BaseAdapter {

    Context context;
    private final String [] iTemNames;
    private final Double [] prices;
    private final int [] images;


    public ListAdapter(Context context, String items[], Double prices[], int images[]){
        this.context = context;
        this.iTemNames = items;
        this.prices = prices;
        this.images = images;

    }

    @Override
    public int getCount() {
        return iTemNames.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        ViewHolder viewHolder;

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.single_list_item, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.iName);
            viewHolder.txtPrice = (TextView) convertView.findViewById(R.id.iPrice);
            viewHolder.icon = (ImageView) convertView.findViewById(R.id.appIconIV);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.txtName.setText(iTemNames[position]);
        viewHolder.txtPrice.setText( "Price: " + prices[position].toString());
        viewHolder.icon.setImageResource(images[position]);

        return result;
    }

    private static class ViewHolder {

        TextView txtName;
        TextView txtPrice;
        ImageView icon;

    }

}