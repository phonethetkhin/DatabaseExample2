package com.example.databaseexample2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.databaseexample2.Adapters.SAdapter;
import com.example.databaseexample2.Database.MusicDatabase;
import com.example.databaseexample2.Models.GenreModel;
import com.example.databaseexample2.Models.MusicModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class UpdateMusic extends AppCompatActivity {
    Spinner spnGenreID;
    TextInputEditText tietMusicName,tietArtistName,tietAlbumName,tietDuration,tietYear;
    Button btnUpdate,btnCancel;
    MusicDatabase mdb=new MusicDatabase(UpdateMusic.this);
    List<GenreModel> genreModelList;
    List<MusicModel> musicModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_music);
        spnGenreID=findViewById(R.id.spnGenreID);
        tietMusicName=findViewById(R.id.tietMusicName);
        tietArtistName=findViewById(R.id.tietArtistName);
        tietAlbumName=findViewById(R.id.tietAlbumName);
        tietDuration=findViewById(R.id.tietDuration);
        tietYear=findViewById(R.id.tietYear);

        btnUpdate=findViewById(R.id.btnUpdate);
        btnCancel=findViewById(R.id.btnCancel);
        //
        genreModelList=mdb.GetGenre();
        spnGenreID.setAdapter(new SAdapter(UpdateMusic.this,R.layout.spinnerlistitem,R.id.tvGenreName,genreModelList));

        final MusicModel mmodel=getIntent().getParcelableExtra("UpdateMusic");

        tietMusicName.setText(mmodel.getMusicName()+"");
        tietArtistName.setText(mmodel.getArtistName()+"");
        tietAlbumName.setText(mmodel.getAlbumName()+"");
        tietDuration.setText(mmodel.getDuration()+"");
        tietYear.setText(mmodel.getYear()+"");







        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tietMusicName.getText().toString().isEmpty() || tietArtistName.getText().toString().isEmpty() || tietAlbumName.getText().toString().isEmpty() ||
                        tietDuration.getText().toString().isEmpty() || tietYear.getText().toString().isEmpty()) {
                    Toast.makeText(UpdateMusic.this, "Please Fill ALl Information", Toast.LENGTH_LONG).show();
                }
                else {
                    mdb.UpdateMusic(mmodel.getMusicID(), genreModelList.get(spnGenreID.getSelectedItemPosition()).getGenreID(), Integer.parseInt(tietYear.getText().toString()), tietMusicName.getText().toString(),
                            tietArtistName.getText().toString(), tietAlbumName.getText().toString(), tietDuration.getText().toString());
                    finish();
                    Toast.makeText(UpdateMusic.this,"Updated Successfully",Toast.LENGTH_LONG).show();

                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
