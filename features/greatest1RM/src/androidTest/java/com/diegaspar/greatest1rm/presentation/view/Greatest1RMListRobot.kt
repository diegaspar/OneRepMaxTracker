package com.diegaspar.greatest1rm.presentation.view

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.diegaspar.greatest1rm.R
import org.hamcrest.CoreMatchers.not
import org.junit.Rule

internal fun greatest1RMList(func: Greatest1RMListRobot.() -> Unit) = Greatest1RMListRobot().apply {
    func()
    finish()
}

internal class Greatest1RMListRobot {

    @get:Rule
    var activityTestRule = IntentsTestRule(Greatest1RMListActivity::class.java, true, false)


    init {
        activityTestRule.launchActivity(null)
    }

    fun finish() {
        activityTestRule.finishActivity()
    }

    fun checkLoadingIsDisplayed() {
        onView(withId(R.id.progress_bar)).check(matches(isDisplayed()))
    }

    fun checkLoadingIsNotDisplayed() {
        onView(withId(R.id.progress_bar)).check(matches(not(isDisplayed())))
    }

    fun checkRetryButtonIsDisplayed() {
        onView(withId(R.id.retry_button)).check(matches(isDisplayed()))
    }

    fun checkRetryButtonIsNotDisplayed() {
        onView(withId(R.id.retry_button)).check(matches(not(isDisplayed())))
    }

    fun checkRecyclerViewIsDisplayed() {
        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()))
    }

    fun checkRecyclerViewIsNotDisplayed() {
        onView(withId(R.id.recycler_view)).check(matches(not(isDisplayed())))
    }
}