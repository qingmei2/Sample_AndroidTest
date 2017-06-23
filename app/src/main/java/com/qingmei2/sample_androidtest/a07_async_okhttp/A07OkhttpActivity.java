package com.qingmei2.sample_androidtest.a07_async_okhttp;

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

public class A07OkhttpActivity extends AppCompatActivity {

    @BindView(R.id.tv_name)
    TextView tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a07_okhttp);
        ButterKnife.bind(this);
        
        requestHttp();
    }

    private void requestHttp() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpProvider.getOkHttpInstance())
                .build();
        GitHubService service = retrofit.create(GitHubService.class);

        service.getUser("qingmei2").enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                tvName.setText(user.name);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                tvName.setText(t.getMessage());
            }
        });

    }
}
