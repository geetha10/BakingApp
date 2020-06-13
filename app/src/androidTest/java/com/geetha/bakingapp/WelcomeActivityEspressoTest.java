package com.geetha.bakingapp;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.idling.CountingIdlingResource;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.geetha.bakingapp.ui.activities.RecipeActivity;
import com.geetha.bakingapp.ui.activities.WelcomeActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
public class WelcomeActivityEspressoTest {

    @Rule
    public IntentsTestRule <WelcomeActivity> activityRule =
            new IntentsTestRule <> (WelcomeActivity.class);

    @Test
    public void ensureRecyclerViewIsPresent() {
        onView (withId (R.id.recipies_recycler_view))
                .check (matches ((isDisplayed ())));

        IdlingResource ir = new CountingIdlingResource ("countingResource", true);
        IdlingRegistry.getInstance ().register (ir);

        onView (withId (R.id.recipies_recycler_view))
                .perform (RecyclerViewActions.actionOnItemAtPosition (0, click ()));
        intended (hasComponent (RecipeActivity.class.getName ()));
    }
}
