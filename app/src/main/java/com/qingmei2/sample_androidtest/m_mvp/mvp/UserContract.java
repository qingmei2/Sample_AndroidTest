package com.qingmei2.sample_androidtest.m_mvp.mvp;

import com.qingmei2.sample_androidtest.a_espresso.a07_async_okhttp.User;

import rx.Observable;

/**
 * Created by QingMei on 2017/6/27.
 * desc:
 */

public interface UserContract {

    interface UserView {

        void onShowUserData(User user);

        void onError(String errorMessage);
    }

    interface UserPresenter {

        void loadUserInfo(String userName);

    }

    interface UserModel {

        Observable<User> loadUserInfo(String userName);

    }

}
