package com.qingmei2.sample_androidtest.m_mvp.mvp.presenter;

import com.google.gson.Gson;
import com.qingmei2.sample_androidtest.a_espresso.a07_async_okhttp.User;
import com.qingmei2.sample_androidtest.m_mvp.api.UserServiceManager;
import com.qingmei2.sample_androidtest.m_mvp.mvp.UserContract;
import com.qingmei2.sample_androidtest.m_mvp.mvp.UserModelImpl;
import com.qingmei2.sample_androidtest.m_mvp.mvp.UserPresenterIpml;
import com.qingmei2.sample_androidtest.mock.base.MockAssestsReader;
import com.qingmei2.sample_androidtest.mock.base.MockRxUnitTestTools;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import rx.Observable;

import static com.qingmei2.sample_androidtest.mock.base.MockAssets.USER_JSON;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by QingMei on 2017/6/27.
 * desc:
 */
public class UserPresenterTest {

    UserContract.UserModel model;
    UserContract.UserView view;
    UserContract.UserPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockRxUnitTestTools.asyncToSync();

        view = mock(UserContract.UserView.class);

        UserServiceManager serviceManager = mock(UserServiceManager.class);
        model = new UserModelImpl(serviceManager);

        presenter = new UserPresenterIpml(model, view);
    }

    @Test
    public void loadUserInfoTest() throws Exception {
        User user = new Gson().fromJson(MockAssestsReader.readFile(USER_JSON), User.class);

        when(model.loadUserInfo(anyString())).thenReturn(Observable.just(user));

        presenter.loadUserInfo("qingmei2");

        //拦截器
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);

        verify(view).onShowUserData(captor.capture());

        User result = captor.getValue(); // 捕获的User
        Assert.assertEquals(result.login, "qingmei2");
        Assert.assertEquals(result.name, "青梅");
    }

}