package com.example.databaseexample2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.sqlite.SQLiteDatabase;
import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.Toast;

import com.example.databaseexample2.Adapters.GenreAdapter;
import com.example.databaseexample2.Database.MusicDatabase;
import com.example.databaseexample2.Models.GenreModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class SaveGenre extends AppCompatActivity {
    TextInputEditText tietGenreName;
    Button btnSave;
    MusicDatabase mdb;
    RecyclerView rvGenre;
    List<GenreModel> genreModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_genre);
        tietGenreName = findViewById(R.id.tietGenreName);
        rvGenre = findViewById(R.id.rvGenre);
        btnSave = findViewById(R.id.btnSave);
        mdb = new MusicDatabase(SaveGenre.this);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(tietGenreName.getText().toString().trim())) {
                    tietGenreName.setError("Fill Genre Name");
                    Toast.makeText(SaveGenre.this, "Please Fill Genre_Name", Toast.LENGTH_LONG).show();
                    tietGenreName.requestFocus();

                } else {
                    if (mdb.InsertGenre(tietGenreName.getText().toString())) {
                        Toast.makeText(SaveGenre.this, "Save Successfully", Toast.LENGTH_LONG).show();
                        onResume();
                        return;


                    } else {
                        Toast.makeText(SaveGenre.this, "Save Fail", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });
       GridLayoutManager gmanager=new GridLayoutManager(SaveGenre.this,2,RecyclerView.VERTICAL,false);
        rvGenre.setLayoutManager(gmanager);
        rvGenre.setHasFixedSize(true);




    }

    @Override
    protected void onResume() {
        super.onResume();
        GetGenre();

        GenreAdapter gadapter=new GenreAdapter(genreModelList);
        rvGenre.setAdapter(gadapter);
        gadapter.notifyDataSetChanged();
    }

    private void GetGenre()
    {
        genreModelList=mdb.GetGenre();
    }

    }

