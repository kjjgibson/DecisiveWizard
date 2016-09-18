package com.giraffetech.decisivewizard.listener;

import com.giraffetech.decisivewizard.model.Scroll;

public interface ScrollListItemHandler {

    void onDecideClick(Scroll scroll);

    void onCustomDecideClick(Scroll scroll);

}