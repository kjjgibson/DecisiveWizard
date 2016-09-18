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
import com.giraffetech.decisivewizard.listener.ScrollItemListItemHandler;
import com.giraffetech.decisivewizard.listitem.AddScrollItemListItem;
import com.giraffetech.decisivewizard.listitem.ScrollItemListItem;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IAdapter;
import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter.adapters.FooterAdapter;
import com.mikepenz.fastadapter.adapters.ItemAdapter;
import com.mikepenz.fastadapter_extensions.drag.ItemTouchCallback;
import com.mikepenz.fastadapter_extensions.drag.SimpleDragCallback;

import java.util.ArrayList;

public class CreateScrollFragment extends Fragment implements ItemTouchCallback, ScrollItemListItemHandler, FastAdapter.OnClickListener {

    private FastAdapter mFastAdapter;
    private ItemAdapter<ScrollItemListItem> mScrollItemListItemItemAdapter;
    private FooterAdapter<AddScrollItemListItem> mFooterAdapter;

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
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewScrollListItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        //Allow dragging and reordering items
        SimpleDragCallback dragCallback = new SimpleDragCallback(this);
        ItemTouchHelper touchHelper = new ItemTouchHelper(dragCallback);
        touchHelper.attachToRecyclerView(recyclerView);

        //Set up the adapters
        mFooterAdapter = new FooterAdapter<>();
        mScrollItemListItemItemAdapter = new ItemAdapter<>();
        mFastAdapter = new FastAdapter();
        mFastAdapter.withSelectable(true);
        mFastAdapter.withOnClickListener(this);

        //Set the adapters on the RecyclerView
        recyclerView.setAdapter(mFooterAdapter.wrap(mScrollItemListItemItemAdapter.wrap(mFastAdapter)));

        //Add the items to the adapters
        mScrollItemListItemItemAdapter.add(getScrollItemListItems());
        mFooterAdapter.add(getFooterItems());

        return view;
    }

    @Override
    public boolean itemTouchOnMove(int oldPosition, int newPosition) {
        boolean allowMove = false;
        //Only allow the item to be moved if it is moving to a position in the ScrollItemListItem adapter
        //Don't allow moving to the FooterAdapter
        if (newPosition < mScrollItemListItemItemAdapter.getAdapterItemCount()) {
            mScrollItemListItemItemAdapter.move(oldPosition, newPosition);
            allowMove = true;
        }
        return allowMove;
    }

    @Override
    public void onRemoveItemClick(ScrollItemListItem itemToRemove) {
        mScrollItemListItemItemAdapter.remove(mScrollItemListItemItemAdapter.getAdapterItems().indexOf(itemToRemove));
    }

    @Override
    public boolean onClick(View v, IAdapter adapter, IItem item, int position) {
        boolean eventConsumed = false;
        if (adapter instanceof FooterAdapter) {
            mScrollItemListItemItemAdapter.add(new ScrollItemListItem("", this));
            eventConsumed = true;
        }
        return eventConsumed;
    }

    private ArrayList<ScrollItemListItem> getScrollItemListItems() {
        final ArrayList<ScrollItemListItem> scrollItemListItems = new ArrayList<>();
        for (int i = 0; i <= 5; i++) {
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
