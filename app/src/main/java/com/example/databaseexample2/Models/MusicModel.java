package com.example.databaseexample2.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class MusicModel implements Parcelable {
    private int MusicID,GenreID,Year;
    private String MusicName,ArtistName,AlbumName,Duration;

    public MusicModel(int musicID, int genreID, int year, String musicName, String artistName, String albumName, String duration) {
        MusicID = musicID;
        GenreID = genreID;
        Year = year;
        MusicName = musicName;
        ArtistName = artistName;
        AlbumName = albumName;
        Duration = duration;
    }

    public MusicModel(int genreID, int year, String musicName, String artistName, String albumName, String duration) {
        GenreID = genreID;
        Year = year;
        MusicName = musicName;
        ArtistName = artistName;
        AlbumName = albumName;
        Duration = duration;
    }

    public int getMusicID() {
        return MusicID;
    }

    public int getGenreID() {
        return GenreID;
    }

    public int getYear() {
        return Year;
    }

    public String getMusicName() {
        return MusicName;
    }

    public String getArtistName() {
        return ArtistName;
    }

    public String getAlbumName() {
        return AlbumName;
    }

    public String getDuration() {
        return Duration;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.MusicID);
        dest.writeInt(this.GenreID);
        dest.writeInt(this.Year);
        dest.writeString(this.MusicName);
        dest.writeString(this.ArtistName);
        dest.writeString(this.AlbumName);
        dest.writeString(this.Duration);
    }

    protected MusicModel(Parcel in) {
        this.MusicID = in.readInt();
        this.GenreID = in.readInt();
        this.Year = in.readInt();
        this.MusicName = in.readString();
        this.ArtistName = in.readString();
        this.AlbumName = in.readString();
        this.Duration = in.readString();
    }

    public static final Parcelable.Creator<MusicModel> CREATOR = new Parcelable.Creator<MusicModel>() {
        @Override
        public MusicModel createFromParcel(Parcel source) {
            return new MusicModel(source);
        }

        @Override
        public MusicModel[] newArray(int size) {
            return new MusicModel[size];
        }
    };
}
