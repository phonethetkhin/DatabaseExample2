package com.example.databaseexample2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.databaseexample2.Adapters.SAdapter;
import com.example.databaseexample2.Database.MusicDatabase;
import com.example.databaseexample2.Models.GenreModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class SaveMusic extends AppCompatActivity {
    TextInputEditText tietMusicName,tietArtistName,tietAlbumName,tietYear,tietDuration;
    Spinner spnGenreID;
    Button btnSave, btnCancel,btnShow;
    List<GenreModel> genreModelList;
    MusicDatabase mydb=new MusicDatabase(SaveMusic.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_music);

        tietMusicName=findViewById(R.id.tietMusicName);
        tietArtistName=findViewById(R.id.tietArtistName);
        tietAlbumName=findViewById(R.id.tietAlbumName);
        tietYear=findViewById(R.id.tietYear);
        tietDuration=findViewById(R.id.tietDuration);
        spnGenreID=findViewById(R.id.spnGenreID);
        btnSave=findViewById(R.id.btnMSave);
        btnCancel=findViewById(R.id.btnMCancel);
        btnShow=findViewById(R.id.btnShow);
        genreModelList=mydb.GetGenre();

        spnGenreID.setAdapter(new SAdapter(SaveMusic.this,R.layout.spinnerlistitem,R.id.tvGenreName,genreModelList));
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    if (tietYear.getText().toString().isEmpty() || tietMusicName.getText().toString().isEmpty() || tietArtistName.getText().toString().isEmpty() ||
                            tietAlbumName.getText().toString().isEmpty() ||
                            tietDuration.getText().toString().isEmpty()) {
                        Toast.makeText(SaveMusic.this, "Please Fill All Information", Toast.LENGTH_LONG).show();
                    }
                    else {
                        if (mydb.InsertMusic(genreModelList.get(spnGenreID.getSelectedItemPosition()).getGenreID(), Integer.parseInt(tietYear.getText().toString()), tietMusicName.getText().toString(), tietArtistName.getText().toString(), tietAlbumName.getText().toString(),
                                tietDuration.getText().toString())) {
                            Toast.makeText(SaveMusic.this, "Save Sucessful", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(SaveMusic.this, "Save Fail", Toast.LENGTH_LONG).show();
                        }
                    }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tietYear.setText("");
                tietMusicName.setText("");
                tietArtistName.setText("");
                tietAlbumName.setText("");
                tietDuration.setText("");

            }
        });
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
Intent i=new Intent(SaveMusic.this,ShowMusic.class);
startActivity(i);
            }
        });
    }
}
