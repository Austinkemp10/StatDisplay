package com.bignerdranch.android.statdisplay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.bignerdranch.android.statdisplay.model.Book;
import com.squareup.picasso.Picasso;

import java.io.Serializable;


public class DisplayActivity extends AppCompatActivity implements Serializable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        /*initViews();
        initListeners();
        initObjects();*/

        Intent intent = getIntent();

        String nbook =  getIntent().getStringExtra("testThis");

        //castImage(nbook/*.getThumbnailURL()*/);
        Log.v("VERBOSE", "You are in the onCreate method! of DisplayActivity" + nbook/*.getThumbnailURL()*/);
    }

    public void initViews() {
        //Not currently needed for this activity
    }

    public void initListeners() {
        //Not currently needed for this activity
    }

    public void initObjects() {
    }


    public void castImage(String imageUrl) {
        //Using the Picasso Library
        ImageView i = (ImageView)findViewById(R.id.image);
        Picasso.with(getApplicationContext()).load(imageUrl).into(i);
        Log.v("VERBOSE", "the image url is: " +imageUrl);
    }

}
