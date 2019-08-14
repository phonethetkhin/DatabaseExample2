package com.example.databaseexample2.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.databaseexample2.Models.GenreModel;
import com.example.databaseexample2.R;

import java.util.List;

public class SAdapter extends ArrayAdapter {
    List<GenreModel> genreModelList;

    public SAdapter(Context context, int resource, int textViewResourceId, List<GenreModel> genreModelList) {
        super(context, resource, textViewResourceId);
        this.genreModelList = genreModelList;
    }

    @Override
    public int getCount() {
        return genreModelList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    public View getCustomView(int i, View v, ViewGroup parent) {
        TextView tvSpinner;
        if(v==null) {

            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.spinnerlistitem, parent, false);
        }
        tvSpinner=v.findViewById(R.id.tvSpinner);
        tvSpinner.setText(genreModelList.get(i).getGenreName()+"");
            return v;

    }

    @Override
    public View getDropDownView(int i, @Nullable View v, @NonNull ViewGroup parent) {
        return getCustomView(i,v,parent);
    }

    @Override
    public View getView(int i, View v, ViewGroup parent) {

return getCustomView(i,v,parent);
    }
}
