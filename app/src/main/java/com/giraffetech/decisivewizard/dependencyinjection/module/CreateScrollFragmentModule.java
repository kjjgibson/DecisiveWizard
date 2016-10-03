package com.giraffetech.decisivewizard.dependencyinjection.module;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.giraffetech.decisivewizard.adapter.ScrollItemAdapter;
import com.giraffetech.decisivewizard.callback.DragItemTouchHelperCallback;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class CreateScrollFragmentModule extends BaseContextModule {

    public CreateScrollFragmentModule(Context context) {
        super(context);
    }

    @Singleton
    @Provides
    public ScrollItemAdapter provideScrollItemAdapter() {
        return new ScrollItemAdapter();
    }

    @Provides
    public RecyclerView.LayoutManager provideLayoutManager() {
        return new LinearLayoutManager(mContext);
    }

    @Provides
    public DragItemTouchHelperCallback provideDragItemTouchHelperCallback(ScrollItemAdapter scrollItemAdapter) {
        return new DragItemTouchHelperCallback(scrollItemAdapter);
    }

    @Provides
    public ItemTouchHelper provideItemTouchHelper(DragItemTouchHelperCallback dragItemTouchHelperCallback) {
        return new ItemTouchHelper(dragItemTouchHelperCallback);
    }

}
