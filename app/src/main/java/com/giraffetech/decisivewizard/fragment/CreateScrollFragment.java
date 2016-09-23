package com.giraffetech.decisivewizard.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.giraffetech.decisivewizard.R;
import com.giraffetech.decisivewizard.adapter.ScrollItemAdapter;
import com.giraffetech.decisivewizard.callback.DragItemTouchHelperCallback;
import com.giraffetech.decisivewizard.listener.OnStartDragListener;
import com.giraffetech.decisivewizard.listitem.ScrollItemListItem;

import java.util.ArrayList;

public class CreateScrollFragment extends Fragment implements OnStartDragListener {

    private ItemTouchHelper mItemTouchHelper;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CreateScrollFragment() {
    }

    public static CreateScrollFragment newInstance() {
        return new CreateScrollFragment();
    }

    //region Lifecycle Methods
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_scroll, container, false);

        ScrollItemAdapter scrollItemListItemItemAdapter = new ScrollItemAdapter(this);
        scrollItemListItemItemAdapter.setItems(getScrollItemListItems());

        Context context = view.getContext();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewScrollListItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(scrollItemListItemItemAdapter);

        mItemTouchHelper = new ItemTouchHelper(new DragItemTouchHelperCallback(scrollItemListItemItemAdapter));
        mItemTouchHelper.attachToRecyclerView(recyclerView);

        return view;
    }
    //endregion Lifecycle Methods

    //region OnStartDragListener Methods
    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
    //endregion OnStartDragListener Methods

    //region Private Methods
    private ArrayList<ScrollItemListItem> getScrollItemListItems() {
        final ArrayList<ScrollItemListItem> scrollItemListItems = new ArrayList<>();
        for (int i = 0; i <= 2; i++) {
            scrollItemListItems.add(new ScrollItemListItem("Item " + i));
        }
        return scrollItemListItems;
    }
    //endregion Private Methods

}
