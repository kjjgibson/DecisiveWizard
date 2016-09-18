package com.giraffetech.decisivewizard.listitem;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;

import com.giraffetech.decisivewizard.R;
import com.giraffetech.decisivewizard.databinding.ScrollItemListItemBinding;
import com.giraffetech.decisivewizard.listener.ScrollItemListItemHandler;
import com.mikepenz.fastadapter.items.AbstractItem;

import java.util.List;

public class ScrollItemListItem extends AbstractItem<ScrollItemListItem, ScrollItemListItem.ViewHolder> {

    //region Fields
    private ScrollItemListItemHandler mHandler;
    private String mItemText;
    //endregion Fields

    //region Constructors
    public ScrollItemListItem() {

    }

    public ScrollItemListItem(@NonNull String itemText, ScrollItemListItemHandler handler) {
        mItemText = itemText;
        mHandler = handler;
    }
    //endregion Constructors

    //region Getters and Setters
    public String getItemText() {
        return mItemText;
    }
    //endregion Getters and Setters

    //region Fast Adapter Methods
    @Override
    public int getType() {
        return R.id.scroll_item_list_item;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.scroll_item_list_item;
    }

    @Override
    public void bindView(ViewHolder viewHolder, List payloads) {
        super.bindView(viewHolder, payloads);

        viewHolder.mBinding.setHandler(mHandler);
        viewHolder.mBinding.setScrollItemListItem(this);
        viewHolder.mBinding.executePendingBindings();
    }

//    @Override
//    public boolean onKeyUp(int keyCode, KeyEvent event) {
//        switch (keyCode) {
//            case KeyEvent.KEYCODE_ENTER:
//                //TODO: add new item to adapter
//                return true;
//            default:
//                return super.onKeyUp(keyCode, event);
//        }
//    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        private ScrollItemListItemBinding mBinding;

        public ViewHolder(View view) {
            super(view);
            mBinding = DataBindingUtil.bind(view);
        }
    }
    //endregion Fast Adapter Methods

}
