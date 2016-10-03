package com.giraffetech.decisivewizard.activity;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import com.giraffetech.decisivewizard.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.giraffetech.decisivewizard.util.CustomMatchers.atPositionOnView;
import static com.giraffetech.decisivewizard.util.CustomMatchers.clickChildViewWithId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ScrollListActivityInstrumentationTest {

    @Rule
    public IntentsTestRule<ScrollListActivity> mActivityRule = new IntentsTestRule<>(ScrollListActivity.class);

    @Test
    public void createActivity_ShowsCardViewByDefault() {
        onView(withId(R.id.recyclerViewScrollCards)).check(matches(isDisplayed()));
    }

    @Test
    public void clickOnToggle_ShowsListView() {
        onView(withId(R.id.action_toggle_scroll_view)).perform(click());

        onView(withId(R.id.recyclerViewScrollList)).check(matches(isDisplayed()));
    }

    @Test
    public void clickOnCard_DoesSomething() {
        onView(withId(R.id.recyclerViewScrollCards)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        //TODO: test goes to the edit scroll activity
    }

    @Test
    public void clickOnCardDecide_DoesSomething() {
        onView(withId(R.id.recyclerViewScrollCards)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.buttonDecide)));

        //TODO: test does something
    }

    @Test
    public void clickOnCardCustomDecide_DoesSomething() {
        onView(withId(R.id.recyclerViewScrollCards)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.buttonCustomDecide)));

        //TODO: test does something
    }

    @Test
    public void clickOnListItem_DoesSomething() {
        onView(withId(R.id.action_toggle_scroll_view)).perform(click());

        onView(withId(R.id.recyclerViewScrollList)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        //TODO: test goes to the edit scroll activity
    }

    @Test
    public void clickOnListItemDecide_DoesSomething() {
        onView(withId(R.id.action_toggle_scroll_view)).perform(click());

        onView(withId(R.id.recyclerViewScrollList)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.imageButtonDecide)));

        //TODO: test does something
    }

    @Test
    public void clickOnListItemCustomDecide_DoesSomething() {
        onView(withId(R.id.action_toggle_scroll_view)).perform(click());

        onView(withId(R.id.recyclerViewScrollList)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.imageButtonCustomDecide)));

        //TODO: test does something
    }

    @Test
    public void clickFab_StartsCreateScrollActivity() {
        onView(withId(R.id.fab)).perform(click());

        intended(hasComponent(CreateScrollActivity.class.getName()));
    }

    @Test
    public void scrollCardList_HasCorrectData() {
        //TODO: Setup data properly

        onView(withId(R.id.recyclerViewScrollCards))
                .check(matches(atPositionOnView(0, withText("Lunch"), R.id.textViewName)));
        onView(withId(R.id.recyclerViewScrollCards))
                .check(matches(atPositionOnView(0, withText("What to have for lunch?!"), R.id.textViewDescription)));
        onView(withId(R.id.recyclerViewScrollCards))
                .check(matches(atPositionOnView(0, withText("Item 1\nItem 1\nItem 1\n..."), R.id.textViewScrollItemsPreview)));
    }

    public void scrollItemList_HasCorrectData() {
        //TODO: Setup data properly

        onView(withId(R.id.action_toggle_scroll_view)).perform(click());

        onView(withId(R.id.recyclerViewScrollList))
                .check(matches(atPositionOnView(0, withText("Lunch"), R.id.textViewName)));
        onView(withId(R.id.recyclerViewScrollList))
                .check(matches(atPositionOnView(0, withText("What to have for lunch?!"), R.id.textViewDescription)));
    }

}
