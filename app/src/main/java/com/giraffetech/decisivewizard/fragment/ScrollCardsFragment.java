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
import com.giraffetech.decisivewizard.adapter.ScrollCardAdapter;
import com.giraffetech.decisivewizard.dependencyinjection.component.DaggerScrollCardsFragmentComponent;
import com.giraffetech.decisivewizard.dependencyinjection.module.ScrollCardsFragmentModule;
import com.giraffetech.decisivewizard.interfaces.DatabaseAccessor;
import com.giraffetech.decisivewizard.listener.OnListFragmentInteractionListener;
import com.giraffetech.decisivewizard.listener.ScrollListItemHandler;
import com.giraffetech.decisivewizard.listitem.ScrollCardItem;
import com.giraffetech.decisivewizard.model.Scroll;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ScrollCardsFragment extends Fragment implements ScrollListItemHandler, ScrollListActivity.OnScrollUpdatedListener {

    //region Dependencies
    @Inject
    protected ScrollCardAdapter mScrollCardAdapter;

    @Inject
    protected RecyclerView.LayoutManager mLayoutManager;
    //endregion Dependencies

    //region Fields
    private OnListFragmentInteractionListener mListener;

    private DatabaseAccessor mDatabaseAccessor;

    private RecyclerView mRecyclerView;
    //endregion Fields

    //region Constructors

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ScrollCardsFragment() {
    }

    public static ScrollCardsFragment newInstance() {
        return new ScrollCardsFragment();
    }
    //endregion Constructors

    //region Lifecycle Methods
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Inject the dependencies
        DaggerScrollCardsFragmentComponent.builder()
                .scrollCardsFragmentModule(new ScrollCardsFragmentModule(getActivity().getApplicationContext()))
                .build()
                .inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scroll_cards, container, false);

        mScrollCardAdapter.setScrollListItemHandler(this);
        mScrollCardAdapter.setItems(getScrollCardItems());

        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mScrollCardAdapter);

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

    //region ScrollListActivity.OnScrollUpdatedListener Methods
    @Override
    public void onScrollUpdated(Scroll scroll) {
        boolean scrollUpdated = false;

        //Check if the Scroll we update is already in the list of ScrollCardItems and update it if it is
        List<ScrollCardItem> scrollCardItems = mScrollCardAdapter.getItems();
        for (int i = 0; i < mScrollCardAdapter.getItemCount(); i++) {
            ScrollCardItem scrollCardItem = scrollCardItems.get(i);

            if (scrollCardItem.getScroll().getDbId() == scroll.getDbId()) {
                scrollCardItem.setScroll(scroll);
                mScrollCardAdapter.notifyItemChanged(i);

                scrollUpdated = true;
                break;
            }
        }

        //If the Scroll was not already in the list, let's add it and notify the RecyclerView
        if (!scrollUpdated) {
            mScrollCardAdapter.addItem(new ScrollCardItem(scroll));
            mScrollCardAdapter.notifyItemInserted(mScrollCardAdapter.getItemCount() - 1);

            //Scroll to the last item so that we can see the newly added Scroll
            mRecyclerView.scrollToPosition(mScrollCardAdapter.getItemCount() - 1);
        }
    }
    //endregion ScrollListActivity.OnScrollUpdatedListener Methods

    //region Private Methods
    private ArrayList<ScrollCardItem> getScrollCardItems() {
        ArrayList<Scroll> scrolls = Scroll.getScrolls(mDatabaseAccessor.getHelper());

        final ArrayList<ScrollCardItem> scrollCardItems = new ArrayList<>();
        for (Scroll scroll : scrolls) {
            scrollCardItems.add(new ScrollCardItem(scroll));
        }

        return scrollCardItems;
    }
    //endregion Private Methods

}
