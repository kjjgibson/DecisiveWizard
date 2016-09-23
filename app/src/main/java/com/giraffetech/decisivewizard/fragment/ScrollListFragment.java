package com.giraffetech.decisivewizard.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.giraffetech.decisivewizard.R;
import com.giraffetech.decisivewizard.adapter.ScrollListAdapter;
import com.giraffetech.decisivewizard.itemdecoration.DividerItemDecoration;
import com.giraffetech.decisivewizard.listener.OnListFragmentInteractionListener;
import com.giraffetech.decisivewizard.listener.ScrollListItemHandler;
import com.giraffetech.decisivewizard.listitem.ScrollListItem;
import com.giraffetech.decisivewizard.model.Scroll;

import java.util.ArrayList;

/**
 * A fragment representing a list of Scrolls.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ScrollListFragment extends Fragment implements ScrollListItemHandler {

    private OnListFragmentInteractionListener mListener;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scroll_list, container, false);

        ScrollListAdapter scrollListAdapter = new ScrollListAdapter(this);
        scrollListAdapter.setItems(getScrollListItems());

        Context context = view.getContext();
        RecyclerView recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity()));
        recyclerView.setAdapter(scrollListAdapter);

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
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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

    //region Private Methods
    private ArrayList<ScrollListItem> getScrollListItems() {
        ArrayList<ScrollListItem> scrollListItems = new ArrayList<>();
        for (int i = 0; i <= 20; i++) {
            scrollListItems.add(new ScrollListItem(new Scroll("Lunch", "What to have for lunch?!", null)));
            scrollListItems.add(new ScrollListItem(new Scroll("Dinner", "Manly dinner options.", null)));
        }

        return scrollListItems;
    }
    //endregion Private Methods

}
