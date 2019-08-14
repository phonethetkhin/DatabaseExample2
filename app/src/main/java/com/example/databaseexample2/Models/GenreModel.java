package com.example.databaseexample2.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class GenreModel implements Parcelable {
    private int GenreID;
    private String GenreName;

    public GenreModel(int genreID, String genreName) {
        GenreID = genreID;
        GenreName = genreName;
    }

    public int getGenreID() {
        return GenreID;
    }

    public String getGenreName() {
        return GenreName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.GenreID);
        dest.writeString(this.GenreName);
    }

    protected GenreModel(Parcel in) {
        this.GenreID = in.readInt();
        this.GenreName = in.readString();
    }

    public static final Parcelable.Creator<GenreModel> CREATOR = new Parcelable.Creator<GenreModel>() {
        @Override
        public GenreModel createFromParcel(Parcel source) {
            return new GenreModel(source);
        }

        @Override
        public GenreModel[] newArray(int size) {
            return new GenreModel[size];
        }
    };
}
