package com.giraffetech.decisivewizard.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.giraffetech.decisivewizard.R;
import com.giraffetech.decisivewizard.activity.ScrollListActivity;
import com.giraffetech.decisivewizard.adapter.ScrollListAdapter;
import com.giraffetech.decisivewizard.dependencyinjection.component.DaggerScrollListFragmentComponent;
import com.giraffetech.decisivewizard.dependencyinjection.module.ScrollListFragmentModule;
import com.giraffetech.decisivewizard.interfaces.DatabaseAccessor;
import com.giraffetech.decisivewizard.itemdecoration.DividerItemDecoration;
import com.giraffetech.decisivewizard.listener.OnListFragmentInteractionListener;
import com.giraffetech.decisivewizard.listener.ScrollListItemHandler;
import com.giraffetech.decisivewizard.listitem.ScrollListItem;
import com.giraffetech.decisivewizard.model.Scroll;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * A fragment representing a list of Scrolls.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ScrollListFragment extends Fragment implements ScrollListItemHandler, ScrollListActivity.OnScrollUpdatedListener {

    //region Dependencies
    @Inject
    protected ScrollListAdapter mScrollListAdapter;

    @Inject
    protected RecyclerView.LayoutManager mLayoutManager;

    @Inject
    protected DividerItemDecoration mDividerItemDecoration;
    //endregion Dependencies

    private OnListFragmentInteractionListener mListener;

    private DatabaseAccessor mDatabaseAccessor;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ScrollListFragment() {
    }

    public static ScrollListFragment newInstance() {
        return new ScrollListFragment();
    }

    //region Lifecycle Methods
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Inject the dependencies
        DaggerScrollListFragmentComponent.builder()
                .scrollListFragmentModule(new ScrollListFragmentModule(getActivity().getApplicationContext()))
                .build()
                .inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scroll_list, container, false);

        mScrollListAdapter.setScrollListItemHandler(this);
        mScrollListAdapter.setItems(getScrollListItems());

        RecyclerView recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(mDividerItemDecoration);
        recyclerView.setAdapter(mScrollListAdapter);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener");
        }

        if (context instanceof DatabaseAccessor) {
            mDatabaseAccessor = (DatabaseAccessor) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement DatabaseAccessor");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        mDatabaseAccessor = null;
    }
    //endregion Lifecycle Methods

    //region ScrollListItemHandler Methods
    @Override
    public void onItemClick(Scroll scroll) {
        mListener.onScrollSelected(scroll);
    }

    @Override
    public void onDecideClick(Scroll scroll) {
        Toast.makeText(getActivity(), "Decide Clicked for scroll: " + scroll.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCustomDecideClick(Scroll scroll) {
        Toast.makeText(getActivity(), "Custom Decide Clicked for scroll: " + scroll.getName(), Toast.LENGTH_SHORT).show();
    }
    //endregion ScrollListItemHandler Methods

    //region ScrollListActivity.OnScrollUpdatedListener

    //endregion ScrollListActivity.OnScrollUpdatedListener
    @Override
    public void onScrollUpdated(Scroll scroll) {
        boolean scrollUpdated = false;

        //Check if the Scroll we update is already in the list of ScrollCardItems and update it if it is
        List<ScrollListItem> scrollListItems = mScrollListAdapter.getItems();
        for (int i = 0; i < mScrollListAdapter.getItemCount(); i++) {
            ScrollListItem scrollListItem = scrollListItems.get(i);

            if (scrollListItem.getScroll().getDbId() == scroll.getDbId()) {
                scrollListItem.setScroll(scroll);
                mScrollListAdapter.notifyItemChanged(i);

                scrollUpdated = true;
                break;
            }
        }

        //If the Scroll was not already in the list, let's add it and notify the RecyclerView
        if (!scrollUpdated) {
            mScrollListAdapter.addItem(new ScrollListItem(scroll));
            mScrollListAdapter.notifyItemInserted(mScrollListAdapter.getItemCount() - 1);
        }
    }

    //region Private Methods
    private ArrayList<ScrollListItem> getScrollListItems() {
        ArrayList<Scroll> scrolls = Scroll.getScrolls(mDatabaseAccessor.getHelper());

        ArrayList<ScrollListItem> scrollListItems = new ArrayList<>();
        for (Scroll scroll : scrolls) {
            scrollListItems.add(new ScrollListItem(scroll));
        }

        return scrollListItems;
    }
    //endregion Private Methods

}
