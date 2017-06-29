package com.qingmei2.sample_androidtest.b_mockito;

/**
 * Created by QingMei on 2017/6/26.
 * desc:
 */

public interface MVPContract {

    public interface View {

        void requestHttp();

        void showRequestData();
    }

    public interface Presenter {

        String getUserName();

        String getPassword();

    }

    public interface Model {

        void getData();
    }

}
