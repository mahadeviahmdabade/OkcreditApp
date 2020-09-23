package com.example.appiness

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.appiness.view.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class UITest {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity>
            = ActivityTestRule(MainActivity::class.java)



    @Test
    fun test_main_functinality() {
        Thread.sleep(4000)
        onView(withText("219382")).check(matches(isDisplayed()))

    }

    @Test
    fun test_search_functinality() {
        Thread.sleep(4000)
        onView(withId(R.id.search)).perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.editText)).perform(typeText("Catalysts"))
        Thread.sleep(1000)
        onView(withId(R.id.ok)).perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.title)).check(matches(isDisplayed()))
    }

    @Test
    fun test_search_no_result() {
        Thread.sleep(4000)
        onView(withId(R.id.search)).perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.editText)).perform(typeText("dsadadsa"))
        Thread.sleep(1000)
        onView(withId(R.id.ok)).perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.title)).check(doesNotExist())
    }
}
