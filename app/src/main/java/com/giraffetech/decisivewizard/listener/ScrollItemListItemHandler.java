package com.giraffetech.decisivewizard.listener;

import com.giraffetech.decisivewizard.listitem.ScrollItemListItem;

public interface ScrollItemListItemHandler {
    void onRemoveItemClick(ScrollItemListItem itemToRemove);

    void addNewItemAfter(ScrollItemListItem item);
}