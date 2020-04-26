package com.example.arsenalfc;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class Player implements Parcelable {

    private String name;
    private String position;
    private String description;
    private String bitmapPath;

    public Player(String name, String position, String description, String bitmapPath) {

        this.name = name;
        this.position = position;
        this.description = description;
        this.bitmapPath = bitmapPath;

    }

    protected Player(Parcel in) {
        name = in.readString();
        position = in.readString();
        description = in.readString();
        bitmapPath = in.readString();
    }

    public static final Creator<Player> CREATOR = new Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBitmapPath() {
        return bitmapPath;
    }

    public void setBitmapPath(String bitmapPath) {
        this.bitmapPath = bitmapPath;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(position);
        dest.writeString(description);
        dest.writeString(bitmapPath);
    }
}
