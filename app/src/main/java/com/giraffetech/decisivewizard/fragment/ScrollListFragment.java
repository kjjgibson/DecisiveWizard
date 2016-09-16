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
import com.giraffetech.decisivewizard.listitem.ScrollListItem;
import com.giraffetech.decisivewizard.model.Scroll;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IAdapter;
import com.mikepenz.fastadapter.adapters.FastItemAdapter;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ScrollListFragment extends Fragment implements ScrollListItem.ScrollListItemHandler {

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scroll_list, container, false);

        final ArrayList<ScrollListItem> scrollListItems = new ArrayList<>();
        for (int i = 0; i <= 20; i++) {
            scrollListItems.add(new ScrollListItem(new Scroll("Lunch", "What to have for lunch?!"), this));
            scrollListItems.add(new ScrollListItem(new Scroll("Dinner", "Manly dinner options."), this));
        }

        Context context = view.getContext();
        RecyclerView recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        FastItemAdapter<ScrollListItem> fastAdapter = new FastItemAdapter<>();
        recyclerView.setAdapter(fastAdapter);
        fastAdapter.add(scrollListItems);
        fastAdapter.withSelectable(true);
        fastAdapter.withOnClickListener(new FastAdapter.OnClickListener<ScrollListItem>() {
            @Override
            public boolean onClick(View v, IAdapter<ScrollListItem> adapter, ScrollListItem scrollListItem, int position) {
                mListener.onScrollSelected(scrollListItem.getScroll());
                return true;
            }
        });

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

    @Override
    public void onDecideClick(Scroll scroll) {
        Toast.makeText(getActivity(), "Decide Clicked for scroll: " + scroll.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCustomDecideClick(Scroll scroll) {
        Toast.makeText(getActivity(), "Custom Decide Clicked for scroll: " + scroll.getName(), Toast.LENGTH_SHORT).show();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnListFragmentInteractionListener {
        void onScrollSelected(Scroll scroll);
    }
}
