package com.giraffetech.decisivewizard.model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.giraffetech.decisivewizard.R;
import com.mikepenz.fastadapter.items.AbstractItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Scroll extends AbstractItem<Scroll, Scroll.ViewHolder> {

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

    //region Fast Adapter Methods
    @Override
    public int getType() {
        return R.id.scroll_list_item;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.scroll_list_item;
    }

    @Override
    public void bindView(ViewHolder viewHolder, List payloads) {
        super.bindView(viewHolder, payloads);

        viewHolder.name.setText(mName);
        viewHolder.description.setText(mDescription);
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView name;
        protected TextView description;

        public ViewHolder(View view) {
            super(view);
            this.name = (TextView) view.findViewById(R.id.textViewName);
            this.description = (TextView) view.findViewById(R.id.textViewDescription);
        }
    }
    //endregion Fast Adapter Methods

}
