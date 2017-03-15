package com.giraffetech.decisivewizard.listener;

import com.giraffetech.decisivewizard.model.Scroll;

public interface ScrollListItemHandler {

    void onItemClick(Scroll scroll);

    void onDecideClick(Scroll scroll);

    void onCustomDecideClick(Scroll scroll);

}