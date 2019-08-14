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
import com.example.databaseexample2.Models.MusicModel;
import com.example.databaseexample2.R;
import com.example.databaseexample2.UpdateMusic;

import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder> {
    List<MusicModel> musicModelList;


    public MusicAdapter(List<MusicModel> musicModelList) {
        this.musicModelList = musicModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.musiclistitem,parent,false);
        ViewHolder holder=new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, final int position) {
        h.tvMusicName.setText(musicModelList.get(position).getMusicName()+"");
        h.tvArtistName.setText(musicModelList.get(position).getArtistName()+"");
        h.tvAlbumName.setText(musicModelList.get(position).getAlbumName()+"");
        h.tvDuration.setText(musicModelList.get(position).getDuration()+"");
        h.tvYear.setText(musicModelList.get(position).getYear()+"");
        h.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(view.getContext(), UpdateMusic.class);
                Bundle b=new Bundle();
                b.putParcelable("UpdateMusic",musicModelList.get(position));
                i.putExtras(b);
                notifyDataSetChanged();
                view.getContext().startActivity(i);
            }
        });
        h.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MusicDatabase mdb=new MusicDatabase(view.getContext());
                mdb.RemoveMovie(musicModelList.get(position).getGenreID());
                Toast.makeText(view.getContext(),"Remove Successfully",Toast.LENGTH_LONG).show();
                musicModelList.remove(musicModelList.get(position));
                notifyItemRemoved(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return musicModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvMusicName, tvArtistName,tvAlbumName,tvDuration,tvYear;
        Button btnUpdate,btnRemove;
        public ViewHolder(@NonNull View v) {
            super(v);
            tvMusicName=v.findViewById(R.id.tvMusicName);
            tvArtistName=v.findViewById(R.id.tvArtistName);
            tvAlbumName=v.findViewById(R.id.tvAlbumName);
            tvDuration=v.findViewById(R.id.tvDuration);
            tvYear=v.findViewById(R.id.tvYear);
            btnUpdate=v.findViewById(R.id.btnUpdate);
            btnRemove=v.findViewById(R.id.btnRemove);
        }
    }
}
