package com.qingmei2.sample_androidtest.b_mockito.b01_simple;

import com.qingmei2.sample_androidtest.b_mockito.b01simple.MVPContract;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by QingMei on 2017/6/26.
 * desc:
 */

public class B01SimpleActivityTest {


    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void sampleTest1() throws Exception {
        //mock creation
        ArrayList<String> mockList = mock(ArrayList.class);

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

        //我们定义，当mockPresenter调用getUserName()时，返回qingmei2
        when(mockPresenter.getUserName()).thenReturn("qingmei2");
        String userName = mockPresenter.getUserName();
        //校验 是否mockPresenter调用了getUserName()方法
        verify(mockPresenter).getUserName();
        //断言 userName为qingmei2
        Assert.assertEquals("qingmei2",userName);

        //校验 是否mockPresenter调用了getPassword()方法
//        verify(mockPresenter).getPassword();
        String password = mockPresenter.getPassword();  //因为未定义返回值，默认返回null
        verify(mockPresenter).getPassword();
        Assert.assertEquals(password,null);
    }
}