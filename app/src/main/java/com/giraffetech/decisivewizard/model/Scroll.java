package com.giraffetech.decisivewizard.model;

import java.util.ArrayList;
import java.util.Date;

public class Scroll {

    //region Fields
    private String mName;
    private String mDescription;
    private Date mCreatedAt;
    private ArrayList<String> mScrollItems;
    //endregion Fields

    //region Constructors
    public Scroll() {

    }

    public Scroll(String name, String description) {
        mName = name;
        mDescription = description;
    }
    //endregion Constructors

    //region Getters and Setters
    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public Date getCreatedAt() {
        return mCreatedAt;
    }

    public ArrayList<String> getScrollItems() {
        return mScrollItems;
    }

    public void setScrollItems(ArrayList<String> scrollItems) {
        mScrollItems = scrollItems;
    }
    //endregion Getters and Setters

}
