package com.example.databaseexample2.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.databaseexample2.Models.GenreModel;
import com.example.databaseexample2.Models.MusicModel;

import java.util.ArrayList;
import java.util.List;

public class MusicDatabase extends SQLiteOpenHelper {
    private static final String DB_NAME="com.example.databaseexample2.Database.MusicDatabase";
    private static final int DB_VERSION=1;

    private final String GENRE_TABLE="Genre";
    private final String MUSIC_TABLE="Music";




    public MusicDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase mydb) {
        mydb.execSQL("CREATE TABLE "+GENRE_TABLE+"(Genre_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,Genre_Name TEXT)");
        mydb.execSQL("CREATE TABLE "+MUSIC_TABLE+"(Music_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,Genre_ID INTEGER,Year INTEGER,Music_Name TEXT,Artist_Name TEXT,Album_Name TEXT,Duration TEXT)");
        Log.e("Create","Create Successful");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public boolean InsertGenre(String GenreName)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("Genre_Name",GenreName);
        try {


            db.insert(GENRE_TABLE, null, cv);
            db.close();

            return true;
        }
        catch (Exception e)
        {
            db.close();
            return false;
        }
    }
    public List<GenreModel> GetGenre()
    {
        List<GenreModel> genreModelList=new ArrayList<>();
        SQLiteDatabase mdb=this.getReadableDatabase();
        Cursor cs=mdb.rawQuery("SELECT * FROM "+GENRE_TABLE,null);
        if(cs.moveToFirst())
        {
            while (cs.isAfterLast()==false)
            {
            GenreModel model=new GenreModel(cs.getInt(0),cs.getString(cs.getColumnIndex("Genre_Name")));
            genreModelList.add(model);

cs.moveToNext();
            }
        }
cs.close();
        return genreModelList;


    }
    public void UpdateGenre(int GenreID, String GenreName)
    {
        SQLiteDatabase mdb=this.getWritableDatabase();
        mdb.execSQL("UPDATE "+GENRE_TABLE+" SET Genre_Name='"+GenreName+"' WHERE Genre_ID="+GenreID+";");
        mdb.close();
    }
    public void RemoveGenre(int GenreID)
    {
        SQLiteDatabase mdb=this.getWritableDatabase();
        mdb.execSQL("DELETE FROM "+GENRE_TABLE+" WHERE Genre_ID="+GenreID+";");


    }

public boolean InsertMusic(int GenreID,int Year,String MusicName,String ArtistName, String AlbumName, String Duration)
{
    SQLiteDatabase db=this.getWritableDatabase();
    ContentValues cv=new ContentValues();
    cv.put("Genre_ID",GenreID);
    cv.put("Year",Year);
    cv.put("Music_Name",MusicName);
    cv.put("Artist_Name",ArtistName);
    cv.put("Album_Name",AlbumName);
    cv.put("Duration",Duration);



try {
    db.insert(MUSIC_TABLE, null, cv);
    db.close();

    return true;
}
catch (Exception e)
{
    db.close();
    return false;
}
}
public List<MusicModel> GetMovie(int GenreID)
{
    List<MusicModel> musicModelList=new ArrayList<>();
    SQLiteDatabase db=this.getReadableDatabase();
    Cursor cs=db.rawQuery("SELECT * FROM "+MUSIC_TABLE+" WHERE Genre_ID="+GenreID,null);
    if(cs.moveToFirst())
    {
        while(!cs.isAfterLast())
        {
            MusicModel mmodel=new MusicModel(cs.getInt(0),cs.getInt(cs.getColumnIndex("Genre_ID")),cs.getInt(cs.getColumnIndex("Year")),cs.getString(cs.getColumnIndex("Music_Name")),
                    cs.getString(cs.getColumnIndex("Artist_Name")),
                    cs.getString(cs.getColumnIndex("Album_Name")),cs.getString(cs.getColumnIndex("Duration")));
            musicModelList.add(mmodel);
            cs.moveToNext();

        }

    }
    cs.close();
    return musicModelList;
}
/*
public void UpdateMovie(int MusicID,int GenreID,int Year,String MusicName,String ArtistName,String AlbumName,String Duration)
{
    SQLiteDatabase db=this.getWritableDatabase();
    ContentValues cv=new ContentValues();
    cv.put("Genre_ID",GenreID);
    cv.put("Year",Year);
    cv.put("Music_Name",MusicName);
    cv.put("Artist_Name",ArtistName);
    cv.put("Album_Name",AlbumName);
    cv.put("Duration",Duration);
try {
    db.update(MUSIC_TABLE, cv, "Music_ID=" + MusicID, null);
    db.close();
}
catch (Exception e)
{
    Log.e("Error","Cant' Update");
}
}
*/
public void UpdateMusic(int MusicID,int GenreID, int Year,String MusicName,String ArtistName,String AlbumName,String Duration)
{
    SQLiteDatabase db=this.getWritableDatabase();
    db.execSQL("UPDATE "+MUSIC_TABLE+" SET Genre_ID="+GenreID+", Year="+Year+",Music_Name='"+MusicName+"',Artist_Name='"+ArtistName+"',Album_Name='"+AlbumName+"',Duration='"+Duration+"' WHERE Music_ID="+MusicID);
    db.close();


}
public void RemoveMovie(int GenreID)
{
    SQLiteDatabase db=this.getWritableDatabase();
    db.execSQL("DELETE FROM "+MUSIC_TABLE+" WHERE Genre_ID="+GenreID);
    db.close();
}

}
