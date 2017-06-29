package com.qingmei2.sample_androidtest.b_mockito;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by QingMei on 2017/6/29.
 * <p>
 * Mockito基础API  二
 * Demo地址：
 * https://static.javadoc.io/org.mockito/mockito-core/2.8.9/org/mockito/Mockito.html#answer_stubs
 */

public class B02SimpleTest {

    @Mock
    List mockList;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * 为回调方法做测试
     * https://static.javadoc.io/org.mockito/mockito-core/2.8.9/org/mockito/Mockito.html#answer_stubs
     */
    @Test
    public void callBackTest() throws Exception {

        //在最初的Mockito里也没有这个具有争议性的特性。我们建议使用thenReturn() 或thenThrow()来打桩。这两种方法足够用于测试或者测试驱动开发。
        when(mockList.add(anyString())).thenAnswer(new Answer<Boolean>() {

            @Override
            public Boolean answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                Object mock = invocation.getMock();
                return false;
            }
        });
        System.out.println(mockList.add("第1次返回false"));

        //lambda表达式
        when(mockList.add(anyString())).then(invocation -> true);
        System.out.println(mockList.add("第2次返回true"));

        when(mockList.add(anyString())).thenReturn(false);
        System.out.println(mockList.add("第3次返回false"));
    }

    /**
     * doReturn()、doThrow()、doAnswer()、doNothing()、doCallRealMethod()系列方法的运用
     * https://static.javadoc.io/org.mockito/mockito-core/2.8.9/org/mockito/Mockito.html#do_family_methods_stubs
     */
    @Test
    public void returnTest() throws Exception {
        //返回值为null的函数，可以通过这种方式进行测试

        doAnswer(invocation -> {
            System.out.println("测试无返回值的函数");
            return null;
        }).when(mockList).clear();

        doThrow(new RuntimeException("测试无返回值的函数->抛出异常"))
                .when(mockList).add(eq(1), anyString());

        doNothing().when(mockList).add(eq(2), anyString());

//        doReturn("123456").when(mockList).add(eq(3), anyString());    //不能把空返回值的函数与doReturn关联

        mockList.clear();
        mockList.add(2, "123");
        mockList.add(3, "123");
        mockList.add(4, "123");
        mockList.add(5, "123");

        //但是请记住这些add实际上什么都没有做，mock对象中仍然什么都没有
        System.out.print(mockList.get(4));
    }

    /**
     * 监控真实对象
     * https://static.javadoc.io/org.mockito/mockito-core/2.8.9/org/mockito/Mockito.html#spy
     */
    @Test
    public void spyTest() throws Exception {
        List list = new ArrayList();
        List spyList = spy(list);

        //当spyList调用size()方法时，return100
        when(spyList.size()).thenReturn(100);

        spyList.add("one");
        spyList.add("two");

        System.out.println("spyList第一个元素" + spyList.get(0));
        System.out.println("spyList.size = " + spyList.size());

        verify(spyList).add("one");
        verify(spyList).add("two");

        //请注意！下面这行代码会报错！ java.lang.IndexOutOfBoundsException: Index: 10, Size: 2
        //不可能 : 因为当调用spy.get(0)时会调用真实对象的get(0)函数,此时会发生异常，因为真实List对象是空的
//        when(spyList.get(10)).thenReturn("ten");

        //应该这么使用

        doReturn("ten").when(spyList).get(9);
        doReturn("eleven").when(spyList).get(10);

        System.out.println("spyList第10个元素" + spyList.get(9));
        System.out.println("spyList第11个元素" + spyList.get(10));

        //Mockito并不会为真实对象代理函数调用，实际上它会拷贝真实对象。因此如果你保留了真实对象并且与之交互
        //不要期望从监控对象得到正确的结果。当你在监控对象上调用一个没有被stub的函数时并不会调用真实对象的对应函数，你不会在真实对象上看到任何效果。

        //因此结论就是 : 当你在监控一个真实对象时，你想在stub这个真实对象的函数，那么就是在自找麻烦。或者你根本不应该验证这些函数。
    }

    /**
     * 为接下来的断言捕获参数(API1.8+)
     * https://static.javadoc.io/org.mockito/mockito-core/2.8.9/org/mockito/Mockito.html#captors
     */
    @Test
    public void captorTest() throws Exception {
        Student student = new Student();
        student.setName("qingmei2");

        ArgumentCaptor<Student> captor = ArgumentCaptor.forClass(Student.class);
        mockList.add(student);
        verify(mockList).add(captor.capture());

        Student value = captor.getValue();

        Assert.assertEquals(value.getName(),"qingmei2");
    }

    @Data
    private class Student {
        private String name;
    }
}
