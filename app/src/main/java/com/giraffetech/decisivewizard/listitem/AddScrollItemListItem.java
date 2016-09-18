package com.giraffetech.decisivewizard.listitem;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.giraffetech.decisivewizard.R;
import com.mikepenz.fastadapter.items.AbstractItem;

import java.util.List;

public class AddScrollItemListItem extends AbstractItem<AddScrollItemListItem, AddScrollItemListItem.ViewHolder> {

    //region Constructors
    public AddScrollItemListItem() {

    }
    //endregion Constructors

    //region Fast Adapter Methods
    @Override
    public int getType() {
        return R.id.add_scroll_item_list_item;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.add_scroll_item_list_item;
    }

    @Override
    public void bindView(ViewHolder viewHolder, List payloads) {
        super.bindView(viewHolder, payloads);
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View view) {
            super(view);
        }
    }
    //endregion Fast Adapter Methods

}
