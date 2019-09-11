package com.bignerdranch.android.statdisplay.helpers;

import android.app.Activity;
import android.content.Context;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class InputValidation {
    private Context context;

    /* Constructor
     *      initializes the context to the variable
     */
    public InputValidation(Context context) {
        this.context = context;
    }



    /* isEditTextFilled
     *      checks if the arguments text input is filled
     *
     *      return: boolean
     */
    public boolean isEditTextFilled(EditText textInput, String message) {
        String value = textInput.getText().toString().trim();

        if(value.equals("")) {
            textInput.setError(message);
            hideKeyboardFrom(textInput);
            return false;
        } else {
            return true;
        }
    }


    /* isEditTextEmail
     *      This method checks if the input is a valid email
     *
     *      return: boolean
     */
    public boolean isEditTextEmail(EditText emailText, String message) {
        String value = emailText.getText().toString().trim();

        if(value.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            emailText.setError(message);
            hideKeyboardFrom(emailText);
            return false;
        }
        else {
            return true;
        }
    }


    /* isEditTextMatch
     *
     *      This method checks if two EditText inputs match. This is useful
     *      for checking if the passwords match and if not returning an
     *      error
     *
     *      return: boolean
     */
    public boolean isEditTextMatch(EditText textOne, EditText textTwo, String message) {
        String value1 = textOne.getText().toString().trim();
        String value2 = textTwo.getText().toString().trim();

        if(!value1.contentEquals(value2)) {
            textTwo.setError(message);
            hideKeyboardFrom(textTwo);
            return false;
        }
        else {
            return true;
        }
    }



    /* hideKeyboardFrom
     *
     *      This method is used to hide the keyboard
     */
    private void hideKeyboardFrom(View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}
