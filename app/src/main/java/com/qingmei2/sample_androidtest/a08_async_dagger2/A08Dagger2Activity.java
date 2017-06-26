package com.qingmei2.sample_androidtest.a08_async_dagger2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.qingmei2.sample_androidtest.R;
import com.qingmei2.sample_androidtest.a07_async_okhttp.User;
import com.qingmei2.sample_androidtest.a08_async_dagger2.api.GitHubServiceManager;
import com.qingmei2.sample_androidtest.a08_async_dagger2.di.ComponentHolder;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 本文案例参照下文博客的开发模式进行单元测试
 * http://www.jianshu.com/p/9eb2a94df005
 *
 * 在这个开发模式下，原文作者是将真正的网络请求在单元测试时Mock了一个本地的网络请求
 * 单元测试时，每次请求网络数据实际上都是在请求本地数据进行模拟网络环境
 *
 * 这种开发模式的优点：将界面的代码和业务代码在单元测试时进行解耦，分别测试（保证了单元测试的粒度）
 */
public class A08Dagger2Activity extends AppCompatActivity {

    @Inject
    GitHubServiceManager serviceManager;

    @BindView(R.id.tv_name)
    TextView tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a08_dagger2);
        ButterKnife.bind(this);
        inject();
    }

    private void inject() {
        ComponentHolder.getAppComponent()
                .inject(this);
    }

    @OnClick(R.id.btn_http)
    public void onViewClicked() {
        serviceManager.getUser("qingmei2")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(User::getLogin)
                .subscribe(this::showData);
    }

    public void showData(String login){
        tvName.setText(login);
    }
}
