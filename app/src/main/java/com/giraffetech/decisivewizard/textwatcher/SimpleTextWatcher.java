package com.giraffetech.decisivewizard.textwatcher;

import android.text.Editable;
import android.text.TextWatcher;

public class SimpleTextWatcher implements TextWatcher {

    //region Fields
    private SimpleTextWatcherListener mListener;
    //endregion Fields

    //region Constructors
    public SimpleTextWatcher(SimpleTextWatcherListener listener) {
        mListener = listener;
    }

    public SimpleTextWatcher() {

    }
    //endregion Constructors

    //region Getters and Setters
    public void setListener(SimpleTextWatcherListener listener) {
        mListener = listener;
    }
    //endregion Getters and Setters

    //region TextWatcher Methods
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        //Do nothing
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        //Do nothing
    }

    @Override
    public void afterTextChanged(Editable s) {
        String string = s.toString();
        //If the last character entered was a new line, add a new list item to the adapter
        if (string.length() > 0 && string.substring(string.length() - 1).equals("\n")) {
            if (mListener != null) {
                mListener.onEnterKeyPressed();
            }
            //Remove the new line character
            s.delete(string.length() - 1, string.length());
        }
    }
    //endregion TextWatcher Methods

    public interface SimpleTextWatcherListener {
        void onEnterKeyPressed();
    }
}