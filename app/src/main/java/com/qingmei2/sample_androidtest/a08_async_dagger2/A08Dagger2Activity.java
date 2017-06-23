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
                .subscribe(tvName::setText);
    }
}
