package com.qingmei2.sample_androidtest.a_espresso.a08_async_dagger2.di;

import com.qingmei2.sample_androidtest.a_espresso.a07_async_okhttp.User;
import com.qingmei2.sample_androidtest.a_espresso.a08_async_dagger2.api.GitHubServiceManager;

import rx.Observable;

/**
 * Created by QingMei on 2017/6/23.
 * desc:
 */

class MockGitHubServiceManager extends GitHubServiceManager {

    @Override
    public Observable<User> getUser(String user) {
        User user1 = new User();
        user1.login = user;
        //延迟三秒
//        return Observable.just(user1).delay(3, TimeUnit.SECONDS);
        return Observable.just(user1);
    }
}
