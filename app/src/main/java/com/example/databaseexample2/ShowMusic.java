package com.example.databaseexample2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.databaseexample2.Adapters.MusicAdapter;
import com.example.databaseexample2.Adapters.SAdapter;
import com.example.databaseexample2.Database.MusicDatabase;
import com.example.databaseexample2.Models.GenreModel;
import com.example.databaseexample2.Models.MusicModel;

import java.util.List;

public class ShowMusic extends AppCompatActivity {
Spinner spnGenreID;
RecyclerView rvMusic;
List<MusicModel> musicModelList;
List<GenreModel> genreModelList;

MusicDatabase mdb=new MusicDatabase(ShowMusic.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_music);
        spnGenreID=findViewById(R.id.spnGenreID);
        rvMusic=findViewById(R.id.rvMusic);
    genreModelList=mdb.GetGenre();
        spnGenreID.setAdapter(new SAdapter(ShowMusic.this,R.layout.spinnerlistitem,R.id.tvGenreName,genreModelList));

spnGenreID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        musicModelList=mdb.GetMovie(genreModelList.get(spnGenreID.getSelectedItemPosition()).getGenreID());

        GridLayoutManager gmanager=new GridLayoutManager(ShowMusic.this,2,RecyclerView.VERTICAL,false);
        rvMusic.setLayoutManager(gmanager);
        rvMusic.setHasFixedSize(true);
        onResume();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
});

    }

    @Override
    protected void onResume() {
        super.onResume();
        musicModelList=mdb.GetMovie(genreModelList.get(spnGenreID.getSelectedItemPosition()).getGenreID());

        MusicAdapter madapter=new MusicAdapter(musicModelList);
        rvMusic.setAdapter(madapter);
        madapter.notifyDataSetChanged();


    }
}
