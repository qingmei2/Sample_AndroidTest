package com.qingmei2.sample_androidtest.m_mvp.mvp;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by QingMei on 2017/6/27.
 * desc:
 */

public class UserPresenterIpml implements UserContract.UserPresenter {

    UserContract.UserModel userModel;
    UserContract.UserView userView;

    public UserPresenterIpml(UserContract.UserModel userModel, UserContract.UserView userView) {
        this.userModel = userModel;
        this.userView = userView;
    }

    @Override
    public void loadUserInfo(String userName) {
        userModel.loadUserInfo(userName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userView::onShowUserData,
                        error -> userView.onError(error.getMessage()));
    }
}
