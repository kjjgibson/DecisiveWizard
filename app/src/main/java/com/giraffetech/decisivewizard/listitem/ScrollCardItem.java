package com.giraffetech.decisivewizard.listitem;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.giraffetech.decisivewizard.model.Scroll;

import java.util.List;

public class ScrollCardItem {

    //region Fields
    private Scroll mScroll;
    //endregion Fields

    //region Constructors
    public ScrollCardItem(@NonNull Scroll scroll) {
        mScroll = scroll;
    }
    //endregion Constructors

    //region Getters and Setters
    public Scroll getScroll() {
        return mScroll;
    }

    public String getName() {
        return mScroll.getName();
    }

    public String getDescription() {
        return mScroll.getDescription();
    }

    public String getScrollItemsPreview() {
        List<String> scrollItems = mScroll.getScrollItems();
        if (scrollItems.size() > 3) {
            scrollItems = scrollItems.subList(0, 3);
            scrollItems.add("...");
        }

        return TextUtils.join("\n", scrollItems);
    }
    //endregion Getters and Setters

}
