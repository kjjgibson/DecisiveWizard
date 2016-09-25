package com.giraffetech.decisivewizard.listitem;

import com.giraffetech.decisivewizard.model.Scroll;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ScrollListItemTest {

    //Class under test
    private ScrollListItem mScrollListItem;

    private Scroll mScroll = new Scroll("Giraffe", "Description", null);

    @Test
    public void getScroll() {
        mScrollListItem = new ScrollListItem(mScroll);

        assertEquals(mScroll, mScrollListItem.getScroll());
    }

    @Test
    public void getName() {
        mScrollListItem = new ScrollListItem(mScroll);

        assertEquals("Giraffe", mScrollListItem.getName());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void getName_NullScroll() {
        mScrollListItem = new ScrollListItem(null);

        assertEquals("", mScrollListItem.getName());
    }

    @Test
    public void getDescription() {
        mScrollListItem = new ScrollListItem(mScroll);

        assertEquals("Description", mScrollListItem.getDescription());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void getDescription_NullDescription() {
        mScrollListItem = new ScrollListItem(null);

        assertEquals("", mScrollListItem.getDescription());
    }

}
