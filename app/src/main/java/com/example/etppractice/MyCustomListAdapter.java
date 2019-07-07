package com.example.etppractice;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyCustomListAdapter extends BaseAdapter {

    ArrayList<ListItem> listItems;

    public MyCustomListAdapter( ArrayList<ListItem> listItems) {
        this.listItems = listItems;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_layout,null);

        TextView textView=view.findViewById(R.id.list_text);
        ImageView imageView=view.findViewById(R.id.list_image);

        textView.setText(ListItemDetails.name[position]);
        imageView.setImageResource(ListItemDetails.img[position]);

        return view;
    }
}
