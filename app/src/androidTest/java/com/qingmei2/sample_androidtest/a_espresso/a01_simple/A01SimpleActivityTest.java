package com.qingmei2.sample_androidtest.a_espresso.a01_simple;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.qingmei2.sample_androidtest.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class A01SimpleActivityTest {

    @Rule
    public ActivityTestRule<A01SimpleActivity> rule = new ActivityTestRule<>(A01SimpleActivity.class);

    @Test
    public void clickTest() {
        //tvContent是否默认不显示
        onView(ViewMatchers.withId(R.id.tv_content))
                .check(matches(not(isDisplayed())));    //是否不可见

        //检查btn01的text，然后执行点击事件
        onView(withId(R.id.btn01))
                .check(matches(withText("修改内容")))
                .perform(click());

        //检查tv内容是否修改，并且是否可见
        onView(withId(R.id.tv_content))
                .check(matches(withText("hello espresso!")))
                .check(matches(isDisplayed()));
    }

    @Test
    public void loginTest() throws Exception {
        //先清除editText的内容，然后输入，然后关闭软键盘，最后校验内容
        //这里如果要输入中文，使用replaceText()方法代替typeText()
        onView(withId(R.id.et_01))
                .perform(clearText(), replaceText("你好 username"), closeSoftKeyboard())
                .check(matches(withText("你好 username")));

        //点击登录
        onView(withId(R.id.btn02))
                .perform(click());

        //校验内容
        onView(withId(R.id.tv_content))
                .check(matches(withText("success")))
                .check(matches(isDisplayed()));

        onView(withId(R.id.et_01))
                .check(matches(withText("")))           //内容是否为""
                .check(matches(withHint("请输入账户名")))         //hint内容是否为"请输入账户名"
                .check(matches(withHint(containsString("账户名"))));       //hint内容是否包含"账户名"
    }
}
