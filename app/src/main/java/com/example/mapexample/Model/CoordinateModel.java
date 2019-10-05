package com.example.mapexample.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class CoordinateModel implements Parcelable {
    String title,latitude,longitude;

    public CoordinateModel(String title, String latitude, String longitude) {
        this.title = title;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public CoordinateModel() {
    }

    public String getTitle() {
        return title;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.latitude);
        dest.writeString(this.longitude);
    }

    protected CoordinateModel(Parcel in) {
        this.title = in.readString();
        this.latitude = in.readString();
        this.longitude = in.readString();
    }

    public static final Parcelable.Creator<CoordinateModel> CREATOR = new Parcelable.Creator<CoordinateModel>() {
        @Override
        public CoordinateModel createFromParcel(Parcel source) {
            return new CoordinateModel(source);
        }

        @Override
        public CoordinateModel[] newArray(int size) {
            return new CoordinateModel[size];
        }
    };
}
