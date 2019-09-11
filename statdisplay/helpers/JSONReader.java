package com.bignerdranch.android.statdisplay.helpers;

import com.bignerdranch.android.statdisplay.model.Book;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JSONReader {
    public void parseBookJson(String jsonString, Book myBook) {
        JsonObject jsonObject = new JsonParser().parse(jsonString).getAsJsonObject();
        String preview = jsonObject.getAsJsonObject("ISBN:" + myBook.getISBN()).get("preview").getAsString();
        String thumbnail_url = jsonObject.getAsJsonObject("ISBN:" + myBook.getISBN()).get("thumbnail_url").getAsString();
        String preview_url = jsonObject.getAsJsonObject("ISBN:" + myBook.getISBN()).get("preview_url").getAsString();
        String info_url = jsonObject.getAsJsonObject("ISBN:" + myBook.getISBN()).get("info_url").getAsString();


        myBook.setPreviewType(preview);
        myBook.setThumbnailURL(thumbnail_url);
        myBook.setPreviewURL(preview_url);
        myBook.setInfoURL(info_url);

    }
}
