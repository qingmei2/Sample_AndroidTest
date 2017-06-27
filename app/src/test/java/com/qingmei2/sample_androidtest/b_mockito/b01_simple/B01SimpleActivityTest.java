package com.qingmei2.sample_androidtest.b_mockito.b01_simple;

import com.qingmei2.sample_androidtest.b_mockito.b01simple.MVPContract;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by QingMei on 2017/6/26.
 * desc:
 */

public class B01SimpleActivityTest {

    private ArrayList mockList;

    @Before
    public void setUp() throws Exception {
        //mock creation
        mockList = mock(ArrayList.class);
    }

    @Test
    public void sampleTest1() throws Exception {
        //using mock object
        mockList.add("one");
        mockList.clear();

        //verification
        verify(mockList).add("one");
        verify(mockList).clear();
    }

    @Test
    public void sampleTest2() throws Exception {
        //我们可以直接mock一个借口，即使我们并未声明它
        MVPContract.Presenter mockPresenter = mock(MVPContract.Presenter.class);
        when(mockPresenter.getUserName()).thenReturn("qingmei2"); //我们定义，当mockPresenter调用getUserName()时，返回qingmei2
        String userName = mockPresenter.getUserName();

        verify(mockPresenter).getUserName(); //校验 是否mockPresenter调用了getUserName()方法
        Assert.assertEquals("qingmei2", userName); //断言 userName为qingmei2

//        verify(mockPresenter).getPassword();  //校验 是否mockPresenter调用了getPassword()方法
        String password = mockPresenter.getPassword();  //因为未定义返回值，默认返回null
        verify(mockPresenter).getPassword();
        Assert.assertEquals(password, null);
    }

    @Test
    public void argumentMatchersTest3() throws Exception {
        when(mockList.get(anyInt())).thenReturn("不管请求第几个参数 我都返回这句");
        System.out.println(mockList.get(0));
        System.out.println(mockList.get(39));

        //当mockList调用addAll()方法时，「匹配器」如果传入的参数list size==2，返回true；
        when(mockList.addAll(argThat(getListMatcher()))).thenReturn(true);
        //根据API文档，我们也可以使用lambda表达式: 「匹配器」如果传入的参数list size==3，返回true；
//        when(mockList.addAll(argThat(list -> list.size() == 3))).thenReturn(true);
        //我们不要使用太严格的参数Matcher，也许下面会更好
//        when(mockList.addAll(argThat(notNull()));


        boolean b1 = mockList.addAll(Arrays.asList("one", "two"));
        boolean b2 = mockList.addAll(Arrays.asList("one", "two", "three"));

        verify(mockList).addAll(argThat(getListMatcher()));
        Assert.assertTrue(b1);
        Assert.assertTrue(!b2);
    }

    private ListOfTwoElements getListMatcher() {
        return new ListOfTwoElements();
    }

    /**
     * 匹配器，用来测试list是否有且仅存在两个元素
     */
    class ListOfTwoElements implements ArgumentMatcher<List> {
        public boolean matches(List list) {
            return list.size() == 2;
        }

        public String toString() {
            //printed in verification errors
            return "[list of 2 elements]";
        }
    }

    @Test
    public void simpleTest4() throws Exception {
        //我们也可以测试方法调用的次数
        mockList.add("once");

        mockList.add("twice");
        mockList.add("twice");

        mockList.add("three times");
        mockList.add("three times");
        mockList.add("three times");

        verify(mockList).add("once");  //验证mockList.add("once")调用了一次 - times(1) is used by default
        verify(mockList, times(1)).add("once");//验证mockList.add("once")调用了一次

        //调用多次校验
        verify(mockList, times(2)).add("twice");
        verify(mockList, times(3)).add("three times");

        //从未调用校验
        verify(mockList, never()).add("four times");

        //至少、至多调用校验
        verify(mockList, atLeastOnce()).add("three times");
        verify(mockList, atMost(5)).add("three times");
//        verify(mockList, atLeast(2)).add("five times"); //这行代码不会通过
    }

    @Test
    public void throwTest5() {
        doThrow(new NullPointerException("throwTest5.抛出空指针异常")).when(mockList).clear();
        doThrow(new IllegalArgumentException("你的参数似乎有点问题")).when(mockList).add(anyInt());

        mockList.add("string");
        mockList.add(12);
        mockList.clear();
    }
}