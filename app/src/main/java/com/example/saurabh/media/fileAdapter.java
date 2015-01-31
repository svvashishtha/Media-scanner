package com.example.saurabh.media;

/**
 * Created by Saurabh on 1/30/2015.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.io.File;

/**
 * Created by Saurabh on 1/30/2015.
 */
public class fileAdapter extends BaseAdapter{
    Context context;
    File files[];
    public fileAdapter(Context context, File files[])
    {
        this.context = context;
        this.files = files;
    }
    @Override
    public int getCount() {
        return files.length;
    }

    @Override
    public Object getItem(int position) {
        return files[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.row_layout,parent,false);
        TextView textView = (TextView)row.findViewById(R.id.row);
        textView.setText(files[position].getName());
        return row;
    }
}

