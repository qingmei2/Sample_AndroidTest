package com.qingmei2.sample_androidtest.b_mockito;

import com.qingmei2.sample_androidtest.a_espresso.a07_async_okhttp.User;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

/**
 * Created by QingMei on 2017/6/26.
 * desc:Mockito基础API  一
 */

public class B01SimpleTest {

    private ArrayList mockList;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
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

    /**
     * 我们也可以测试方法调用的次数
     * https://static.javadoc.io/org.mockito/mockito-core/2.8.9/org/mockito/Mockito.html#exact_verification
     *
     * @throws Exception
     */
    @Test
    public void simpleTest4() throws Exception {
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

    /**
     * 异常抛出测试
     * https://static.javadoc.io/org.mockito/mockito-core/2.8.9/org/mockito/Mockito.html#stubbing_with_exceptions
     */
    @Test
    public void throwTest5() {
        doThrow(new NullPointerException("throwTest5.抛出空指针异常")).when(mockList).clear();
        doThrow(new IllegalArgumentException("你的参数似乎有点问题")).when(mockList).add(anyInt());

        mockList.add("string");
        mockList.add(12);
        mockList.clear();
    }

    /**
     * 验证执行执行顺序
     * https://static.javadoc.io/org.mockito/mockito-core/2.8.9/org/mockito/Mockito.html#in_order_verification
     *
     * @throws Exception
     */
    @Test
    public void orderTest6() throws Exception {
        List singleMock = mock(List.class);

        singleMock.add("first add");
        singleMock.add("second add");

        InOrder inOrder = inOrder(singleMock);

        //inOrder保证了方法的顺序执行
        inOrder.verify(singleMock).add("first add");
        inOrder.verify(singleMock).add("second add");

        List firstMock = mock(List.class);
        List secondMock = mock(List.class);

        firstMock.add("first add");
        secondMock.add("second add");

        InOrder inOrder1 = inOrder(firstMock, secondMock);

        //下列代码会确认是否firstmock优先secondMock执行add方法
        inOrder1.verify(firstMock).add("first add");
        inOrder1.verify(secondMock).add("second add");
    }

    /**
     * 确保mock对象从未进行过交互
     * https://static.javadoc.io/org.mockito/mockito-core/2.8.9/org/mockito/Mockito.html#never_verification
     *
     * @throws Exception
     */
    @Test
    public void noInteractedTest7() throws Exception {
        List firstMock = mock(List.class);
        List secondMock = mock(List.class);
        List thirdMock = mock(List.class);

        firstMock.add("one");

        verify(firstMock).add("one");

        verify(firstMock, never()).add("two");

        firstMock.add(thirdMock);
        // 确保交互(interaction)操作不会执行在mock对象上
//        verifyZeroInteractions(firstMock); //test failed,因为firstMock和其他mock对象有交互
        verifyZeroInteractions(secondMock, thirdMock);   //test pass
    }


    /**
     * 简化mock对象的创建,请注意，一旦使用@Mock注解，一定要在测试方法调用之前调用(比如@Before注解的setUp方法)
     * MockitoAnnotations.initMocks(testClass);
     */
    @Mock
    List mockedList;
    @Mock
    User mockedUser;

    @Test
    public void initMockTest8() throws Exception {
        mockedList.add("123");
        mockedUser.setLogin("qingmei2");
    }

    /**
     * 方法连续调用的测试
     * https://static.javadoc.io/org.mockito/mockito-core/2.8.9/org/mockito/Mockito.html#stubbing_consecutive_calls
     */
    @Test
    public void continueMethodTest9() throws Exception {
        when(mockedUser.getName())
                .thenReturn("qingmei2")
                .thenThrow(new RuntimeException("方法调用第二次抛出异常"))
                .thenReturn("qingemi2 第三次调用");

        //另外一种方式
        when(mockedUser.getName()).thenReturn("qingmei2 1", "qingmei2 2", "qingmei2 3");

        String name1 = mockedUser.getName();

        try {
            String name2 = mockedUser.getName();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        String name3 = mockedUser.getName();

        System.out.println(name1);
        System.out.println(name3);
    }
}