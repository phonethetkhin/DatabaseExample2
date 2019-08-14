package com.example.databaseexample2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.databaseexample2.Database.MusicDatabase;
import com.example.databaseexample2.Models.GenreModel;
import com.google.android.material.textfield.TextInputEditText;

public class UpdateGenre extends AppCompatActivity {
TextInputEditText tietUpdateGenreName;
Button btnCUpdateGenre, btnCancel;
MusicDatabase mdb=new MusicDatabase(UpdateGenre.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_genre);
        tietUpdateGenreName=findViewById(R.id.tietUpdateGenreName);
        btnCUpdateGenre=findViewById(R.id.btnCUpdateGenre);
        btnCancel=findViewById(R.id.btnCancel);
        GenreModel gmodel=getIntent().getParcelableExtra("GenreUpdate");
       final int id=gmodel.getGenreID();

       tietUpdateGenreName.setText(gmodel.getGenreName());

btnCUpdateGenre.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if(tietUpdateGenreName.getText().toString().isEmpty())
        {
            Toast.makeText(UpdateGenre.this,"Please Fill New GenreName",Toast.LENGTH_LONG).show();
        }
        else {
            mdb.UpdateGenre(id, tietUpdateGenreName.getText().toString());
            Toast.makeText(UpdateGenre.this, "Updated Sucessfully", Toast.LENGTH_SHORT).show();
            finish();
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
