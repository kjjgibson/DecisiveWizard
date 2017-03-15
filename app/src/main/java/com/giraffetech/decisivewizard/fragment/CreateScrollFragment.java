package com.giraffetech.decisivewizard.fragment;

import android.app.Fragment;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.giraffetech.decisivewizard.R;
import com.giraffetech.decisivewizard.adapter.ScrollItemAdapter;
import com.giraffetech.decisivewizard.databinding.FragmentCreateScrollBinding;
import com.giraffetech.decisivewizard.dependencyinjection.component.DaggerCreateScrollFragmentComponent;
import com.giraffetech.decisivewizard.dependencyinjection.module.CreateScrollFragmentModule;
import com.giraffetech.decisivewizard.interfaces.DatabaseAccessor;
import com.giraffetech.decisivewizard.listener.OnStartDragListener;
import com.giraffetech.decisivewizard.listitem.ScrollItemListItem;
import com.giraffetech.decisivewizard.model.Scroll;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class CreateScrollFragment extends Fragment implements OnStartDragListener {

    //region Constants
    public static final String PARCEL_KEY_SCROLL = "scroll";
    //endregion Constants

    //region Dependencies
    @Inject
    protected ScrollItemAdapter mScrollItemAdapter;

    @Inject
    protected RecyclerView.LayoutManager mLayoutManager;

    @Inject
    protected ItemTouchHelper mItemTouchHelper;
    //endregion Dependencies

    //region Fields
    private Scroll mScroll;

    private DatabaseAccessor mDatabaseAccessor;
    //endregion Fields

    //region Constructors

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CreateScrollFragment() {
    }

    public static CreateScrollFragment newInstance(@NonNull Scroll scroll) {
        CreateScrollFragment fragment = new CreateScrollFragment();

        Bundle args = new Bundle();
        args.putParcelable(PARCEL_KEY_SCROLL, Parcels.wrap(scroll));
        fragment.setArguments(args);

        return fragment;
    }
    //endregion Constructors

    //region Lifecycle Methods
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        mScroll = Parcels.unwrap(args.getParcelable(PARCEL_KEY_SCROLL));

        //Inject the dependencies
        DaggerCreateScrollFragmentComponent.builder()
                .createScrollFragmentModule(new CreateScrollFragmentModule(getActivity().getApplicationContext()))
                .build()
                .inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentCreateScrollBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_scroll, container, false);
        View view = binding.getRoot();

        binding.setScroll(mScroll);

        mScrollItemAdapter.setOnStartDragListener(this);
        mScrollItemAdapter.setItems(getScrollItemListItems());

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewScrollListItems);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mScrollItemAdapter);

        mItemTouchHelper.attachToRecyclerView(recyclerView);

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();

        updateAndSaveScroll();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof DatabaseAccessor) {
            mDatabaseAccessor = (DatabaseAccessor) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement DatabaseAccessor");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mDatabaseAccessor = null;
    }
    //endregion Lifecycle Methods

    //region OnStartDragListener Methods
    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
    //endregion OnStartDragListener Methods

    //region Public Methods
    public Scroll getScroll() {
        updateAndSaveScroll();
        return mScroll;
    }
    //endregion Public Methods

    //region Private Methods
    private ArrayList<ScrollItemListItem> getScrollItemListItems() {
        List<String> scrollItems = mScroll.getScrollItems();

        final ArrayList<ScrollItemListItem> scrollItemListItems = new ArrayList<>();
        for (String scrollItem : scrollItems) {
            scrollItemListItems.add(new ScrollItemListItem(scrollItem));
        }
        return scrollItemListItems;
    }

    private void updateAndSaveScroll() {
        //Update the Scroll's items with the contents of the adapter
        ArrayList<String> scrollListItems = new ArrayList<>();
        ArrayList<ScrollItemListItem> scrollItemListItems = mScrollItemAdapter.getItems();
        for (ScrollItemListItem scrollItemListItem : scrollItemListItems) {
            scrollListItems.add(scrollItemListItem.getItemText());
        }
        mScroll.setScrollItems(scrollListItems);

        //Save the Scroll every time we pause the Fragment
        mScroll.save(mDatabaseAccessor.getHelper());
    }
    //endregion Private Methods

}
