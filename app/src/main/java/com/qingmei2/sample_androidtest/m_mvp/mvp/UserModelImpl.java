package com.qingmei2.sample_androidtest.m_mvp.mvp;

import com.qingmei2.sample_androidtest.a_espresso.a07_async_okhttp.User;
import com.qingmei2.sample_androidtest.m_mvp.api.UserServiceManager;

import rx.Observable;

/**
 * Created by QingMei on 2017/6/27.
 * desc:
 */

public class UserModelImpl implements UserContract.UserModel {

    UserServiceManager serviceManager;

    public UserModelImpl(UserServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @Override
    public Observable<User> loadUserInfo(String userName) {
        return serviceManager.getUserInfo(userName);
    }
}
