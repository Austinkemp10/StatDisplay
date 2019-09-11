package com.bignerdranch.android.statdisplay;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import com.bignerdranch.android.statdisplay.helpers.JSONReader;
import com.bignerdranch.android.statdisplay.helpers.InputValidation;
import com.bignerdranch.android.statdisplay.model.Book;
import com.bignerdranch.android.statdisplay.DisplayActivity;


public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = HomeActivity.this;

    private Button bSubmit;
    private EditText eText;

    //Write model for "player"
    private static Book book;
    private InputValidation inputValidation;
    private DisplayActivity display;
    private Intent intentDisplay;

    //values for the book in this activity
    private String bookThumbnail;
    private String bookInfo;
    private String bookPreview;
    private String bookISBN;

    //Global variabless

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initViews();
        initListeners();
        initObjects();
        Log.v("VERBOSE", "You are in the onCreate method!");
    }

    /* initViews
     *      This function initializes the views required for the
     *      HomeActivity.
     */
    private void initViews() {
        bSubmit = (Button) findViewById(R.id.bSubmit);
        eText = (EditText) findViewById(R.id.eText);
    }



    private void initListeners() {
        bSubmit.setOnClickListener(this);
    }

    private void initObjects() {
        inputValidation = new InputValidation(activity);
        book = new Book();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bSubmit:
                Log.v("VERBOSE", "You are in the onClick method!");
                /*When they click the submit button:
                 *      1. Validate they have something entered
                 *      2. Call outside class for API interaction
                 *      3. Either call the new activity here or have
                 *         it called in the API class.
                 */

                submitHandle(book.getURL() + book.getEntryValue() + "" + book.getISBN() + "" + "&" + book.getReturnType());
                intentDisplay = new Intent(activity, DisplayActivity.class);

                //intentDisplay.putExtra("testThis", bookThumbnail);

                startActivity(intentDisplay);
                break;

        }
    }


    /* private class taskHandler
     *      extends AsyncTask
     *
     */
    protected static class taskHandler extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            //Build the URL
            String sendURL = book.getURL() + book.getEntryValue() + "" + book.getISBN() + "&" + book.getReturnType();

            Log.v("VERBOSE", sendURL);

            String jsonString = "";
            try {
                URL url = new URL(sendURL);
                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
                String str = "";
                while(null != (str = br.readLine())) {
                    jsonString += str;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            JSONReader reader = new JSONReader();
            reader.parseBookJson(jsonString, book);

            String responseString = "==========================================================" +
                                    "The book information is:" + "\n" +
                                        "\n" + "\t" + "Preview type:" + "\t" + "\t" + book.getpreviewType() +
                                        "\n" + "\t" + "URL for thumbnail:" + "\t" + "\t" + book.getThumbnailURL() +
                                        "\n" + "\t" + "URL for preview:" + "\t" + "\t" + book.getPreviewURL() +
                                        "\n" + "\t" + "URL for info:" + "\t" + "\t" + book.getInfoURL() +
                                    "==========================================================";

            Log.v("VERBOSE", responseString);


            return responseString;
        }
    }

    /* submitHandle
     *      This function handles the functionality of the application when
     *      the submit button is clicked. Here we will execute all of the necessary
     *      code to make the switch in "onClick" look cleaner
     *
     *      return: void
     */
    private void submitHandle(String sendURL) {
        //Step one - validation
        if(!inputValidation.isEditTextFilled(eText, "Please enter an ISBN value!")) {
            return;
        }
        //Possibly set up check for 10/13 digit ISBN values
        //If there is something entered, attempt to handle the ISBN
        String ISBN = eText.getText().toString().trim();
        //int ISBNint = Integer.parseInt(ISBN);
        book.setISBN(ISBN);

        new taskHandler().execute(sendURL);


    }

    /* emptyEditText
     *      This function empties the text fields in this activity. This
     *      is an activity specific function which is why it isn't in the
     *      InputValidation class.
     *
     *      return: void
     */
    private void emptyEditText() {
        eText.setText(null);
    }

    public Book getBook() {
        return book;
    }

}
