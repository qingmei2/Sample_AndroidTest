package com.qingmei2.sample_androidtest.m_mvp.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.qingmei2.sample_androidtest.R;
import com.qingmei2.sample_androidtest.a_espresso.a07_async_okhttp.User;
import com.qingmei2.sample_androidtest.m_mvp.api.UserServiceManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by QingMei on 2017/6/27.
 * desc:
 */

public class UserActivity extends AppCompatActivity implements UserContract.UserView {

    @BindView(R.id.tv_name)
    TextView tvName;

    UserContract.UserPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a08_dagger2);
        ButterKnife.bind(this);

        UserServiceManager serviceManager = new UserServiceManager();
        UserModelImpl userModel = new UserModelImpl(serviceManager);
        presenter = new UserPresenterIpml(userModel, this);
    }

    @Override
    public void onShowUserData(User user) {
        tvName.setText(user.getLogin());
    }

    @Override
    public void onError(String errorMessage) {
        tvName.setText(errorMessage);
    }

    @OnClick(R.id.btn_http)
    public void onViewClicked() {
        presenter.loadUserInfo("qingmei2");
    }
}
