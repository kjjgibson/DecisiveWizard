package com.giraffetech.decisivewizard.listitem;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.giraffetech.decisivewizard.R;
import com.giraffetech.decisivewizard.databinding.ScrollCardItemBinding;
import com.giraffetech.decisivewizard.listener.ScrollListItemHandler;
import com.giraffetech.decisivewizard.model.Scroll;
import com.mikepenz.fastadapter.items.AbstractItem;

import java.util.List;

public class ScrollCardItem extends AbstractItem<ScrollCardItem, ScrollCardItem.ViewHolder> {

    //region Fields
    private Scroll mScroll;
    private ScrollListItemHandler mScrollListItemHandler;
    //endregion Fields

    //region Constructors
    public ScrollCardItem(Scroll scroll, ScrollListItemHandler scrollListItemHandler) {
        mScroll = scroll;
        mScrollListItemHandler = scrollListItemHandler;
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

    //region Fast Adapter Methods
    @Override
    public int getType() {
        return R.id.scroll_card_item;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.scroll_card_item;
    }

    @Override
    public void bindView(ViewHolder viewHolder, List payloads) {
        super.bindView(viewHolder, payloads);

        viewHolder.mBinding.setHandler(mScrollListItemHandler);
        viewHolder.mBinding.setScrollCardItem(this);
        viewHolder.mBinding.executePendingBindings();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        private ScrollCardItemBinding mBinding;

        public ViewHolder(View view) {
            super(view);
            mBinding = DataBindingUtil.bind(view);
        }
    }
    //endregion Fast Adapter Methods

}
