package com.giraffetech.decisivewizard.listitem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

@RunWith(MockitoJUnitRunner.class)
public class ScrollItemListItemTest {

    //Class under test
    private ScrollItemListItem mScrollItemListItem;

    @Test
    public void getItemText() {
        mScrollItemListItem = new ScrollItemListItem("Giraffe");

        assertEquals("Giraffe", mScrollItemListItem.getItemText());
    }

    @Test
    public void shouldHaveFocus_DefaultsToFalse() {
        mScrollItemListItem = new ScrollItemListItem("");

        assertFalse(mScrollItemListItem.shouldHaveFocus());
    }

}
