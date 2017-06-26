package com.qingmei2.sample_androidtest.a_espresso.a07_async_okhttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.qingmei2.sample_androidtest.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class A07OkhttpActivity extends AppCompatActivity {

    @BindView(R.id.tv_name)
    TextView tvName;
    private GitHubService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a07_okhttp);
        ButterKnife.bind(this);
        
        initHttp();
        requestHttp();
//        requestRxHttp();
    }

    private void initHttp() {
        service = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpProvider.getOkHttpInstance())
                .build()
                .create(GitHubService.class);
    }

    private void requestRxHttp() {
        service.getRxUser("qingmei2")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(User::getLogin)
                .subscribe(tvName::setText,System.out::print);
    }

    private void requestHttp() {
        service.getUser("qingmei2").enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                tvName.setText(user.login);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                tvName.setText(t.getMessage());
            }
        });

    }
}
