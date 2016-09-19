package com.giraffetech.decisivewizard.listitem;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.giraffetech.decisivewizard.R;
import com.giraffetech.decisivewizard.databinding.ScrollItemListItemBinding;
import com.giraffetech.decisivewizard.listener.ScrollItemListItemHandler;
import com.mikepenz.fastadapter.items.AbstractItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScrollItemListItem extends AbstractItem<ScrollItemListItem, ScrollItemListItem.ViewHolder> {

    //region Fields
    //Handler used to communicate back to the class containing the RecyclerView
    private ScrollItemListItemHandler mHandler;

    //The text that should be displayed in the EditText
    private String mItemText;

    //True if this EditText should have focus
    //Should be set to true when an item is newly added to the adapter
    private boolean mShouldHaveFocus = false;
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

    //region Public Methods
    public void setShouldHaveFocus(boolean shouldHaveFocus) {
        mShouldHaveFocus = shouldHaveFocus;
    }
    //endregion Public Methods

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
    public void bindView(final ViewHolder viewHolder, List payloads) {
        super.bindView(viewHolder, payloads);

        //Items should have focus when they are first added to the adapter
        if (mShouldHaveFocus) {
            viewHolder.getScrollItemEditText().requestFocus();
            mShouldHaveFocus = false;
        }

        viewHolder.getScrollItemEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    viewHolder.getRemoveImageButton().setVisibility(View.VISIBLE);
                } else {
                    viewHolder.getRemoveImageButton().setVisibility(View.INVISIBLE);
                }
            }
        });

        viewHolder.getScrollItemEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String string = s.toString();
                //If the last character entered was a new line, add a new list item to the adapter
                if (string.length() > 0 && string.substring(string.length() - 1).equals("\n")) {
                    mHandler.addNewItemAfter(ScrollItemListItem.this);
                    //Remove the new line character
                    s.delete(string.length() - 1, string.length());
                }
            }
        });

        viewHolder.getBinding().setHandler(mHandler);
        viewHolder.getBinding().setScrollItemListItem(this);
        viewHolder.getBinding().executePendingBindings();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        private ScrollItemListItemBinding mBinding;

        @BindView(R.id.editTextScrollItem)
        EditText mScrollItemEditText;

        @BindView(R.id.imageButtonRemoveItem)
        ImageButton mRemoveImageButton;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            mBinding = DataBindingUtil.bind(view);
        }

        protected ScrollItemListItemBinding getBinding() {
            return mBinding;
        }

        protected EditText getScrollItemEditText() {
            return mScrollItemEditText;
        }

        protected ImageButton getRemoveImageButton() {
            return mRemoveImageButton;
        }
    }
    //endregion Fast Adapter Methods

}
