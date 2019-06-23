package com.gowreeshmago.mynotes.NotesObject;

import android.os.Parcel;
import android.os.Parcelable;

public class NotesObject implements Parcelable {

    private String title;
    private String timestamp;
    private String content;


    public NotesObject(String title, String timestamp, String content) {
        this.title = title;
        this.timestamp = timestamp;
        this.content = content;
    }

    public NotesObject() {
    }

    protected NotesObject(Parcel in) {
        title = in.readString();
        timestamp = in.readString();
        content = in.readString();
    }

    public static final Creator<NotesObject> CREATOR = new Creator<NotesObject>() {
        @Override
        public NotesObject createFromParcel(Parcel in) {
            return new NotesObject(in);
        }

        @Override
        public NotesObject[] newArray(int size) {
            return new NotesObject[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }




    //parcelable implemented methods

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(timestamp);
        dest.writeString(content);
    }
}
