package com.giraffetech.decisivewizard.callback;

import android.support.v7.widget.RecyclerView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DragItemTouchHelperCallbackTest {

    @Mock
    private RecyclerView.ViewHolder mMockViewHolder;

    @Mock
    private DragItemTouchHelperCallback.ItemTouchHelperAdapter mMockItemTouchHelperAdapter;

    //Class under test
    private DragItemTouchHelperCallback mDragItemTouchHelperCallback;

    @Test
    public void getMovementFlags() {
        mDragItemTouchHelperCallback = new DragItemTouchHelperCallback(null);

        assertEquals(3342336, mDragItemTouchHelperCallback.getMovementFlags(null, null));
    }

    @Test
    public void onMove_NullAdapter_ReturnsFalse() {
        mDragItemTouchHelperCallback = new DragItemTouchHelperCallback(null);

        boolean moved = mDragItemTouchHelperCallback.onMove(null, mMockViewHolder, mMockViewHolder);

        assertFalse(moved);
    }

    @Test
    public void onMove_WithAdapter_CallsOnItemMoveOnAdapter() {
        //ARRANGE
        when(mMockItemTouchHelperAdapter.onItemMove(anyInt(), anyInt())).thenReturn(true);
        DragItemTouchHelperCallback.ItemTouchHelperAdapter mockAdapter = mMockItemTouchHelperAdapter;
        mDragItemTouchHelperCallback = new DragItemTouchHelperCallback(mockAdapter);

        //ACT
        boolean moved = mDragItemTouchHelperCallback.onMove(null, mMockViewHolder, mMockViewHolder);

        //ASSERT
        verify(mockAdapter).onItemMove(anyInt(), anyInt());
        assertTrue(moved);
    }

}
