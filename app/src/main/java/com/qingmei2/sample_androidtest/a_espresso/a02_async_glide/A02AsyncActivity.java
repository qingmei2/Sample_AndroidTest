package com.qingmei2.sample_androidtest.a_espresso.a02_async_glide;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.qingmei2.sample_androidtest.R;
import com.qingmei2.sample_androidtest.a_espresso.util.EspressoIdlingResource;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class A02AsyncActivity extends AppCompatActivity {

    public static final String IMAGE_LOAD_START = "start";
    public static final String IMAGE_LOAD_FINISH = "finish";


    @BindView(R.id.btn01)
    Button btn01;
    @BindView(R.id.imageView)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a02_async);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn01)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn01:
                //意味着开始了异步
                EspressoIdlingResource.increment();

                Glide.with(this)
                        .load("http://imgsrc.baidu.com/imgad/pic/item/caef76094b36acaf0accebde76d98d1001e99ce7.jpg")
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                btn01.setText("success!");
                                //图片加载成功，结束异步
                                if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
                                    EspressoIdlingResource.decrement();
                                }
                                return false;
                            }
                        }).into(imageView);
                break;
        }
    }

    @VisibleForTesting
    public IdlingResource getCountingIdlingResource() {
        return EspressoIdlingResource.getIdlingResource();
    }
}
