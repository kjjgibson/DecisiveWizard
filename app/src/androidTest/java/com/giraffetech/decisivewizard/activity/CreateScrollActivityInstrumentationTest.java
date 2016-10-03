package com.giraffetech.decisivewizard.activity;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.giraffetech.decisivewizard.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.giraffetech.decisivewizard.util.CustomMatchers.clickChildViewWithId;
import static com.giraffetech.decisivewizard.util.CustomMatchers.withAdapterItemCount;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class CreateScrollActivityInstrumentationTest {

    @Rule
    public ActivityTestRule<CreateScrollActivity> mActivityRule = new ActivityTestRule<>(CreateScrollActivity.class);

    @Test
    public void clickAddItem_AddsNewEmptyRow() {
        onView(withId(R.id.recyclerViewScrollListItems)).perform(
                RecyclerViewActions.actionOnItemAtPosition(3, clickChildViewWithId(R.id.linearLayoutAddItemRow)));

        onView(withId(R.id.recyclerViewScrollListItems)).check(matches(withAdapterItemCount(5)));
    }

    @Test
    public void clickRowItem_ShowsRemoveButton() {
//        onView(withId(R.id.recyclerViewScrollListItems)).perform(
//                RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.editTextScrollItem)));
//
//        onView(withId(R.id.recyclerViewScrollListItems))
//                .check(matches(atPositionOnView(1, isDisplayed(), R.id.imageButtonRemoveItem)));
    }

    @Test
    public void clickRemoveButton_RemovesTheRow() {

    }

    @Test
    public void enterNewLineKeyboardButton_AddsNewEmptyRow() {

    }

    @Test
    public void toolbarBackButton_ExitsActivity() {

    }

    @Test
    public void dragItems_ReordersItems() {

    }

}
