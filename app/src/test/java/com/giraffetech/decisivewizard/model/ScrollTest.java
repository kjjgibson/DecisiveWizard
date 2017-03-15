package com.giraffetech.decisivewizard.model;

import org.junit.Test;

import static junit.framework.Assert.assertNotNull;

public class ScrollTest {

    @Test
    public void scrollItems_DefaultToEmptyList() {
        Scroll scroll = new Scroll();

        assertNotNull(scroll.getScrollItems());
    }

}
