package com.diegaspar.detailgreatest1rm

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import com.diegaspar.detailgreatest1rm.DetailActivityTest.Companion.anyExerciseName
import com.diegaspar.detailgreatest1rm.DetailActivityTest.Companion.anyRepMaxValue
import com.diegaspar.detailgreatest1rm.presentation.view.DetailActivity
import com.diegaspar.navigation.NavigationParams.EXERCISE_NAME
import com.diegaspar.navigation.NavigationParams.ONE_REP_MAX
import org.hamcrest.CoreMatchers.not
import org.junit.Rule


internal fun greatest1RMList(func: Detail1RMRobot.() -> Unit) = Detail1RMRobot().apply {
    func()
    finish()
}

internal class Detail1RMRobot {

    @get:Rule
    var activityTestRule = object : IntentsTestRule<DetailActivity>(
        DetailActivity::class.java, true, true
    ) {
        override fun getActivityIntent(): Intent = Intent()
            .putExtra(EXERCISE_NAME, anyExerciseName)
            .putExtra(ONE_REP_MAX, anyRepMaxValue)
    }


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

    fun checkToastErrorAppeared() {
        onView(withText(DetailActivityTest.anyError)).inRoot(withDecorView(not(activityTestRule.activity.window.decorView)))
            .check(matches(isDisplayed()))
    }

    fun checkChartViewIsDisplayed() {
        onView(withId(R.id.line_chart)).check(matches(isDisplayed()))
    }

    fun checkChartViewIsNotDisplayed() {
        onView(withId(R.id.line_chart)).check(matches(not(isDisplayed())))
    }

    fun checkRowInfoViewIsDisplayed() {
        onView(withId(R.id.one_rep_max_row)).check(matches(isDisplayed()))
    }

    fun validateRowInfoDisplayed() {
        onView(withId(R.id.exercise_name_text)).check(matches(withText(anyExerciseName)))
        onView(withId(R.id.one_rep_max_value_text)).check(matches(withText(anyRepMaxValue.toString())))
    }
}