package com.giraffetech.decisivewizard.listitem;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.giraffetech.decisivewizard.R;
import com.giraffetech.decisivewizard.databinding.ScrollListItemBinding;
import com.giraffetech.decisivewizard.model.Scroll;
import com.mikepenz.fastadapter.items.AbstractItem;

import java.util.List;

public class ScrollListItem extends AbstractItem<ScrollListItem, ScrollListItem.ViewHolder> {

    //region Fields
    private Scroll mScroll;
    private ScrollListItemHandler mScrollListItemHandler;
    //endregion Fields

    //region Constructors
    public ScrollListItem(Scroll scroll, ScrollListItemHandler scrollListItemHandler) {
        mScroll = scroll;
        mScrollListItemHandler = scrollListItemHandler;
    }
    //endregion Constructors

    //region Getters and Setters
    public Scroll getScroll() {
        return mScroll;
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

        viewHolder.mBinding.setHandler(mScrollListItemHandler);
        viewHolder.mBinding.setScroll(mScroll);
        viewHolder.mBinding.executePendingBindings();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        private ScrollListItemBinding mBinding;

        public ViewHolder(View view) {
            super(view);
            mBinding = DataBindingUtil.bind(view);
        }
    }
    //endregion Fast Adapter Methods

    public interface ScrollListItemHandler {

        void onDecideClick(Scroll scroll);

        void onCustomDecideClick(Scroll scroll);
    }


}
