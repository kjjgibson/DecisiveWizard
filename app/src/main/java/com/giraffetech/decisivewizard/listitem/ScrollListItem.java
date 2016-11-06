package com.giraffetech.decisivewizard.listitem;

import android.support.annotation.NonNull;

import com.giraffetech.decisivewizard.model.Scroll;

public class ScrollListItem {

    //region Fields
    @NonNull
    private Scroll mScroll;
    //endregion Fields

    //region Constructors
    public ScrollListItem(@NonNull Scroll scroll) {
        mScroll = scroll;
    }
    //endregion Constructors

    //region Getters and Setters
    @NonNull
    public Scroll getScroll() {
        return mScroll;
    }

    public void setScroll(@NonNull Scroll scroll) {
        mScroll = scroll;
    }

    @NonNull
    public String getName() {
        return mScroll.getName() != null ? mScroll.getName() : "(Unnamed)";
    }

    public String getDescription() {
        return mScroll.getDescription() != null ? mScroll.getDescription() : "";
    }
    //endregion Getters and Setters

}
