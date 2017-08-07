package com.qingmei2.sample_androidtest.m_mvp.model;

import com.annimon.stream.Optional;
import com.qingmei2.sample_androidtest.a_espresso.a07_async_okhttp.User;
import com.qingmei2.sample_androidtest.m_mvp.api.UserService;
import com.qingmei2.sample_androidtest.base.mocks.MockAssets;
import com.qingmei2.sample_androidtest.base.mocks.MockRetrofitHelper;
import com.qingmei2.sample_androidtest.base.mocks.MockAssestsReader;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by QingMei on 2017/6/27.
 * desc:
 */
public class UserModelTest {

    MockRetrofitHelper retrofitHelper;
    UserService userService;

    @Before
    public void setUp() throws Exception {
        retrofitHelper = new MockRetrofitHelper();

        userService = retrofitHelper.create(UserService.class);
    }

    @Test
    public void fileReaderTest() throws Exception {
        Optional.ofNullable(MockAssets.USER_JSON)
                .map(MockAssestsReader::readFile)
                .ifPresentOrElse(System.out::println,
                        ()-> System.out.println("file read failed"));
    }

    @Test
    public void loadUserInfoTest() throws Exception {
        //文件直接采用了绝对路径，请根据个人项目中文件的绝对路径进行设置
        retrofitHelper.setPath(MockAssets.USER_JSON);

        User user = userService.getRxUser("qingmei2")
                .toBlocking()
                .first();

        System.out.println("USER:"+user.toString());
        Assert.assertEquals(user.login, "qingmei2");
        Assert.assertEquals(user.name, "青梅");
    }

}