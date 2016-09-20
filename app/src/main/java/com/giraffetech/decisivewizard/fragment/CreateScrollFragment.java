package com.giraffetech.decisivewizard.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.giraffetech.decisivewizard.R;
import com.giraffetech.decisivewizard.adapter.ScrollItemAdapter;
import com.giraffetech.decisivewizard.listener.ScrollItemListItemHandler;
import com.giraffetech.decisivewizard.listitem.AddScrollItemListItem;
import com.giraffetech.decisivewizard.listitem.ScrollItemListItem;

import java.util.ArrayList;

public class CreateScrollFragment extends Fragment implements ScrollItemListItemHandler {

    private ScrollItemAdapter mScrollItemListItemItemAdapter;
    private RecyclerView mRecyclerView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CreateScrollFragment() {
    }

    public static CreateScrollFragment newInstance() {
        return new CreateScrollFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_scroll, container, false);

        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewScrollListItems);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        //Allow dragging and reordering items
//        SimpleDragCallback dragCallback = new SimpleDragCallback(this);
//        ItemTouchHelper touchHelper = new ItemTouchHelper(dragCallback);
//        touchHelper.attachToRecyclerView(mRecyclerView);

        //Set up the adapters
//        FooterAdapter<AddScrollItemListItem> mFooterAdapter = new FooterAdapter<>();
        mScrollItemListItemItemAdapter = new ScrollItemAdapter(getScrollItemListItems());
//        FastAdapter<IItem> mFastAdapter = new FastAdapter<>();
//        mFastAdapter.withSelectable(true);
//        mFastAdapter.withOnClickListener(this);

        //Set the adapters on the RecyclerView
        mRecyclerView.setAdapter(mScrollItemListItemItemAdapter);

        //Add the items to the adapters
//        mScrollItemListItemItemAdapter.add(getScrollItemListItems());
//        mFooterAdapter.add(getFooterItems());

        return view;
    }

//    @Override
//    public boolean itemTouchOnMove(int oldPosition, int newPosition) {
//        boolean allowMove = false;
//        //Only allow the item to be moved if it is moving to a position in the ScrollItemListItem adapter
//        //Don't allow moving to the FooterAdapter
//        if (newPosition < mScrollItemListItemItemAdapter.getItemCount()) {
//            mScrollItemListItemItemAdapter.move(oldPosition, newPosition);
//            allowMove = true;
//        }
//        return allowMove;
//    }

    @Override
    public void onRemoveItemClick(ScrollItemListItem itemToRemove) {
        int index = mScrollItemListItemItemAdapter.getItems().indexOf(itemToRemove);
        mScrollItemListItemItemAdapter.getItems().remove(index);
        mScrollItemListItemItemAdapter.notifyItemRemoved(index);
    }

    @Override
    public void addNewItemAfter(ScrollItemListItem item) {
        addNewEmptyItem(mScrollItemListItemItemAdapter.getItems().indexOf(item) + 1);
    }

//    @Override
//    public boolean onClick(View v, IAdapter adapter, IItem item, int position) {
//        boolean eventConsumed = false;
//        if (adapter instanceof FooterAdapter) {
//            addNewEmptyItem(mScrollItemListItemItemAdapter.getItemCount() - 1);
//            eventConsumed = true;
//        }
//        return eventConsumed;
//    }

    private void addNewEmptyItem(int position) {
        ScrollItemListItem item = new ScrollItemListItem("", this);
        mScrollItemListItemItemAdapter.getItems().add(position, item);
        mScrollItemListItemItemAdapter.notifyItemInserted(position);
        item.setShouldHaveFocus(true);
    }

    private ArrayList<ScrollItemListItem> getScrollItemListItems() {
        final ArrayList<ScrollItemListItem> scrollItemListItems = new ArrayList<>();
        for (int i = 0; i <= 2; i++) {
            scrollItemListItems.add(new ScrollItemListItem("Item " + i, this));
        }
        return scrollItemListItems;
    }

    private ArrayList<AddScrollItemListItem> getFooterItems() {
        ArrayList<AddScrollItemListItem> footerItems = new ArrayList<>();
        footerItems.add(new AddScrollItemListItem());
        return footerItems;
    }
}
