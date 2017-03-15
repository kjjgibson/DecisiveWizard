package com.giraffetech.decisivewizard.listitem;

import com.giraffetech.decisivewizard.model.Scroll;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
public class ScrollCardItemTest {

    //Class under test
    private ScrollCardItem mScrollCardItem;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Test
    public void getName_ReturnsTheScrollsName() {
        Scroll scroll = new Scroll("Name", "Description", null);
        mScrollCardItem = new ScrollCardItem(scroll);

        assertEquals("Name", mScrollCardItem.getName());
    }

    @Test
    public void getDescription_ReturnsTheScrollsDescription() {
        Scroll scroll = new Scroll("Name", "Description", null);
        mScrollCardItem = new ScrollCardItem(scroll);

        assertEquals("Description", mScrollCardItem.getDescription());
    }

    @Test
    public void getScrollItemsPreview_NoItems_ReturnsEmptyString() {
        Scroll scroll = new Scroll();
        mScrollCardItem = new ScrollCardItem(scroll);

        String preview = mScrollCardItem.getScrollItemsPreview();

        assertEquals("", preview);
    }

    @Test
    public void getScrollItemsPreview_OneItem_ReturnsAllTheItems() {
        mScrollCardItem = new ScrollCardItem(scrollWithItems(1));

        String preview = mScrollCardItem.getScrollItemsPreview();

        assertEquals("Item 1", preview);
    }

    @Test
    public void getScrollItemsPreview_ThreeItems_ReturnsAllTheItems() {
        mScrollCardItem = new ScrollCardItem(scrollWithItems(3));

        String preview = mScrollCardItem.getScrollItemsPreview();

        assertEquals("Item 1\nItem 2\nItem 3", preview);
    }

    @Test
    public void getScrollItemsPreview_FourItems_ReturnsTheFirstThreeItems() {
        mScrollCardItem = new ScrollCardItem(scrollWithItems(4));

        String preview = mScrollCardItem.getScrollItemsPreview();

        assertEquals("Item 1\nItem 2\nItem 3\n...", preview);
    }

    //region Helper Methods
    private Scroll scrollWithItems(int numberOfItems) {
        Scroll scroll = new Scroll();
        ArrayList<String> scrollItems = new ArrayList<>();
        for (int i = 0; i < numberOfItems; i++) {
            scrollItems.add("Item " + (i + 1));
        }
        scroll.setScrollItems(scrollItems);

        return scroll;
    }
    //endregion Helper Methods

}
