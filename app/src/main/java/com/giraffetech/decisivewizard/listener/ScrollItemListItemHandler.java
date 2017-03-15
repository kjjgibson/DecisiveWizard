package com.giraffetech.decisivewizard.listener;

import com.giraffetech.decisivewizard.listitem.ScrollItemListItem;

public interface ScrollItemListItemHandler {
    void removeItem(ScrollItemListItem itemToRemove);

    void addNewItemAfter(ScrollItemListItem item);

    void addNewItemAtEnd();
}