package cn.iocoder.mall.user.test;

import cn.iocoder.mall.user.api.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApplicationTest.class)
public class UserApplicationTest {

    @Autowired
    private UserService userService;

    @Test
    public void test11(){

    }


}
