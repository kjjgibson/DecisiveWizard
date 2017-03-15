package com.giraffetech.decisivewizard.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.giraffetech.decisivewizard.adapter.ScrollItemAdapter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.robolectric.RobolectricTestRunner;

import static org.mockito.Mockito.mock;

@RunWith(RobolectricTestRunner.class)
public class CreateScrollFragmentTest {

    //region Rules
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();
    //endregion Rules

    //region Mocks
    @Mock
    private ScrollItemAdapter mScrollItemAdapter;

    @Mock
    private RecyclerView.LayoutManager mLayoutManager;

    @Mock
    private ItemTouchHelper mItemTouchHelper;
    //endregion Mocks

    //Class under test
    @InjectMocks
    private CreateScrollFragment mCreateScrollFragment;

    @Test
    public void testSomething() {
        mCreateScrollFragment.onCreateView(mock(LayoutInflater.class), mock(RelativeLayout.class), new Bundle());
    }

}
