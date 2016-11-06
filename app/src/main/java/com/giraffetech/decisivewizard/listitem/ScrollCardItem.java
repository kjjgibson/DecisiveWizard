package com.giraffetech.decisivewizard.listitem;

import android.support.annotation.NonNull;

import com.giraffetech.decisivewizard.model.Scroll;

import java.util.List;

public class ScrollCardItem {

    //region Fields
    @NonNull
    private Scroll mScroll;
    //endregion Fields

    //region Constructors
    public ScrollCardItem(@NonNull Scroll scroll) {
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

    @NonNull
    public String getDescription() {
        return mScroll.getDescription() != null ? mScroll.getDescription() : "";
    }

    public String getScrollItemsPreview() {
        StringBuilder previewStringBuilder = new StringBuilder();

        List<String> scrollItems = mScroll.getScrollItems();
        for (int i = 0; i < 3; i++) {
            if (i < scrollItems.size()) {
                previewStringBuilder.append(scrollItems.get(i));
                previewStringBuilder.append("\n");
            }
        }

        if (scrollItems.size() > 3) {
            previewStringBuilder.append("...");
        }

        return previewStringBuilder.toString();
    }
    //endregion Getters and Setters

}
