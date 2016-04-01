package com.pulkit4tech.popularmovies1.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by PULKIT on 2/12/2016.
 */
public class Data_item implements Parcelable{

    public String movie_name;
    public float movie_rating;
    public Double movie_popularity;
    public String movie_released_date;
    public String overview;
    public String poster_url;
    public int id;

    public Data_item(){

    }

    public Data_item(Parcel in){
        movie_name = in.readString();
        movie_rating = in.readFloat();
        movie_popularity = in.readDouble();
        movie_released_date = in.readString();
        overview = in.readString();
        poster_url = in.readString();
        id = in.readInt();
    }


    public static final Creator<Data_item> CREATOR = new Creator<Data_item>() {
        @Override
        public Data_item createFromParcel(Parcel in) {
            return new Data_item(in);
        }

        @Override
        public Data_item[] newArray(int size) {
            return new Data_item[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(movie_name);
        dest.writeFloat(movie_rating);
        dest.writeDouble(movie_popularity);
        dest.writeString(movie_released_date);
        dest.writeString(overview);
        dest.writeString(poster_url);
        dest.writeInt(id);
    }
}
