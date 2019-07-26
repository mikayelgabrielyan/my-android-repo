package instigatemobile.com.cookmaster;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.squareup.spoon.Spoon;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import instigatemobile.com.cookmaster.activities.LoginForgotPasswordActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityTestRule<LoginForgotPasswordActivity> mActivityTestRule = new
            ActivityTestRule<>(LoginForgotPasswordActivity.class);

    private LoginForgotPasswordActivity mActivity;

    @Before
    public void setUp() {
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void testSetEmailInvalidError() {
        Spoon.screenshot(mActivity, "initial_state");

        onView(withId(R.id.input_email)).perform(typeText("test"));
        onView(withId(R.id.input_password)).perform(typeText("password"), closeSoftKeyboard());

        onView(withId(R.id.btn_login)).perform(click());

        Spoon.screenshot(mActivity, "after_login");

        onView(withId(R.id.input_email)).check(matches(hasErrorText(mActivity.getString(R.string.invalide_email))));
    }

    @Test
    public void testSetPasswordInvalidError() {
        Spoon.screenshot(mActivity, "initial_state");

        onView(withId(R.id.input_email)).perform(typeText("student@gmail.com"));
        onView(withId(R.id.input_password)).perform(typeText(""), closeSoftKeyboard());

        onView(withId(R.id.btn_login)).perform(click());

        Spoon.screenshot(mActivity, "after_login");

        onView(withId(R.id.input_password)).check(matches(hasErrorText(mActivity.getString(R.string.valid_password_type))));
    }

    @Test
    public void testSetMismatchError() {
        Spoon.screenshot(mActivity, "initial_state");

        onView(withId(R.id.input_email)).perform(typeText("student@gmail.com"));
        onView(withId(R.id.input_password)).perform(typeText("student"), closeSoftKeyboard());

        onView(withId(R.id.btn_login)).perform(click());

        Spoon.screenshot(mActivity, "after_login");
    }
}