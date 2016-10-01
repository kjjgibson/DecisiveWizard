package com.giraffetech.decisivewizard.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.giraffetech.decisivewizard.R;
import com.giraffetech.decisivewizard.adapter.ScrollItemAdapter;
import com.giraffetech.decisivewizard.dependencyinjection.component.DaggerCreateScrollFragmentComponent;
import com.giraffetech.decisivewizard.listener.OnStartDragListener;
import com.giraffetech.decisivewizard.listitem.ScrollItemListItem;
import com.giraffetech.decisivewizard.dependencyinjection.module.CreateScrollFragmentModule;

import java.util.ArrayList;

import javax.inject.Inject;

public class CreateScrollFragment extends Fragment implements OnStartDragListener {

    //region Dependencies
    @Inject
    protected ScrollItemAdapter mScrollItemAdapter;

    @Inject
    protected RecyclerView.LayoutManager mLayoutManager;

    @Inject
    protected ItemTouchHelper mItemTouchHelper;
    //endregion Dependencies

    //region Constructors

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CreateScrollFragment() {
    }

    public static CreateScrollFragment newInstance() {
        return new CreateScrollFragment();
    }
    //endregion Constructors

    //region Lifecycle Methods
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Inject the dependencies
        DaggerCreateScrollFragmentComponent.builder()
                .createScrollFragmentModule(new CreateScrollFragmentModule(getActivity().getApplicationContext()))
                .build()
                .inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_scroll, container, false);

        mScrollItemAdapter.setOnStartDragListener(this);
        mScrollItemAdapter.setItems(getScrollItemListItems());

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewScrollListItems);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mScrollItemAdapter);

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
