package com.bignerdranch.android.statdisplay.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Book implements Parcelable {
    //Sending values
    private String ISBN;
    private static String returnType = "format=json";
    private static String url = "https://openlibrary.org/api/books?";
    private static String entryValue = "bibkeys=ISBN:";
    private  String previewType;
    private  String thumbnailURL;
    private  String previewURL;
    private  String infoURL;

    public Book() {

    }

    public Book(String ISBN, String previewType, String thumbnailURL, String previewURL, String infoURL) {
        super();
        this.ISBN = ISBN;
        this.previewType = previewType;
        this.thumbnailURL = thumbnailURL;
        this.previewURL = previewURL;
        this.infoURL = infoURL;
    }
    //Return values to model
    /* Getters and setters for the above data values
     *
     */
    public String getISBN() {return ISBN;}

    public void setISBN(String ISBN) {this.ISBN = ISBN;}

    public String getURL() {return url;}

    public String getReturnType() {return returnType;}

    public String getEntryValue() {return entryValue;}

    public void setPreviewType(String previewType) {this.previewType = previewType;}

    public String getpreviewType() {return previewType;}

    public void setThumbnailURL(String thumbnailURL) {this.thumbnailURL = thumbnailURL;}

    public String getThumbnailURL() {return thumbnailURL;}

    public void setPreviewURL(String previewURL) {this.previewURL = previewURL;}

    public String getPreviewURL() { return previewURL;}

    public void setInfoURL(String infoURL) {this.infoURL = infoURL;}

    public String getInfoURL() {return infoURL;}

   public Book getBook() {
        return Book.this;
    }

    /* functions and information for Parcelable object passing between activities.
     *
     *
     *
     */


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getISBN());
        dest.writeString(getpreviewType());
        dest.writeString(getThumbnailURL());
        dest.writeString(getPreviewURL());
        dest.writeString(getInfoURL());
    }

    public Book(Parcel in) {
        setISBN(in.readString());
        setPreviewType(in.readString());
        setThumbnailURL(in.readString());
        setPreviewURL(in.readString());
        setInfoURL(in.readString());
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}
