package com.giraffetech.decisivewizard.viewholder;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class DataBoundViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {

    private T mBinding;

    public DataBoundViewHolder(View view) {
        super(view);
        mBinding = DataBindingUtil.bind(view);
    }

    public T getBinding() {
        return mBinding;
    }

}