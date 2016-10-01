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
import com.giraffetech.decisivewizard.adapter.ScrollCardAdapter;
import com.giraffetech.decisivewizard.dependencyinjection.component.DaggerScrollCardsFragmentComponent;
import com.giraffetech.decisivewizard.listener.OnListFragmentInteractionListener;
import com.giraffetech.decisivewizard.listener.ScrollListItemHandler;
import com.giraffetech.decisivewizard.listitem.ScrollCardItem;
import com.giraffetech.decisivewizard.model.Scroll;
import com.giraffetech.decisivewizard.dependencyinjection.module.ScrollCardsFragmentModule;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ScrollCardsFragment extends Fragment implements ScrollListItemHandler {

    //region Dependencies
    @Inject
    protected ScrollCardAdapter mScrollCardAdapter;

    @Inject
    protected RecyclerView.LayoutManager mLayoutManager;
    //endregion Dependencies

    //region Fields
    private OnListFragmentInteractionListener mListener;
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

        RecyclerView recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mScrollCardAdapter);

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
    private ArrayList<ScrollCardItem> getScrollCardItems() {
        ArrayList<String> scrollItems = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            scrollItems.add("Item 1");
        }

        final ArrayList<ScrollCardItem> scrollCardItems = new ArrayList<>();
        for (int i = 0; i <= 20; i++) {
            scrollCardItems.add(new ScrollCardItem(new Scroll("Lunch", "What to have for lunch?!", scrollItems)));
            scrollCardItems.add(new ScrollCardItem(new Scroll("Dinner", "Manly dinner options.", scrollItems)));
        }

        return scrollCardItems;
    }
    //endregion Private Methods

}
