package com.giraffetech.decisivewizard.viewholder;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.giraffetech.decisivewizard.R;
import com.giraffetech.decisivewizard.databinding.ScrollItemListItemBinding;
import com.giraffetech.decisivewizard.textwatcher.SimpleTextWatcher;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScrollItemViewHolder extends DataBoundViewHolder<ScrollItemListItemBinding> {

    @BindView(R.id.editTextScrollItem)
    EditText mScrollItemEditText;

    @BindView(R.id.imageButtonRemoveItem)
    ImageButton mRemoveImageButton;

    @BindView(R.id.imageViewDragHandle)
    ImageView mDragHandleImageView;

    private SimpleTextWatcher mTextWatcher;

    public ScrollItemViewHolder(View view, SimpleTextWatcher textWatcher) {
        super(view);
        ButterKnife.bind(this, view);
        mTextWatcher = textWatcher;
        mScrollItemEditText.addTextChangedListener(mTextWatcher);
    }

    //region Getters and Setters
    public EditText getScrollItemEditText() {
        return mScrollItemEditText;
    }

    public ImageButton getRemoveImageButton() {
        return mRemoveImageButton;
    }

    public ImageView getDragHandleImageView() {
        return mDragHandleImageView;
    }

    public SimpleTextWatcher getTextWatcher() {
        return mTextWatcher;
    }
    //endregion Getters and Setters

}
