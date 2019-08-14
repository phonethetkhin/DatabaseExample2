package com.example.databaseexample2.Adapters;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databaseexample2.Database.MusicDatabase;
import com.example.databaseexample2.Models.GenreModel;
import com.example.databaseexample2.R;
import com.example.databaseexample2.UpdateGenre;

import java.util.List;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.ViewHolder> {
    List<GenreModel> genreModelList;

    public GenreAdapter(List<GenreModel> genreModelList) {
        this.genreModelList = genreModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.genrelistitem,parent,false);
        ViewHolder holder=new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, final int position) {
        h.tvGenreName.setText(genreModelList.get(position).getGenreName()+"");
        h.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(view.getContext(), UpdateGenre.class);
                Bundle b=new Bundle();
                b.putParcelable("GenreUpdate",genreModelList.get(position));
                i.putExtras(b);

        view.getContext().startActivity(i);
            }
        });
        h.btnRemove.setOnClickListener(new View.OnClickListener() {
         @Override
            public void onClick(View view) {
             MusicDatabase mdb=new MusicDatabase(view.getContext());
             mdb.RemoveGenre(genreModelList.get(position).getGenreID());
             Toast.makeText(view.getContext(),"Remove Successfully",Toast.LENGTH_LONG).show();
             genreModelList.remove(position);
             notifyDataSetChanged();


         }
});
    }

    @Override
    public int getItemCount() {
        return genreModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvGenreName;
        Button btnUpdate,btnRemove;
        public ViewHolder(@NonNull View v) {
            super(v);
            tvGenreName=v.findViewById(R.id.tvGenreName);
            btnUpdate=v.findViewById(R.id.btnUpdate);
            btnRemove=v.findViewById(R.id.btnRemove);
        }
    }
}
