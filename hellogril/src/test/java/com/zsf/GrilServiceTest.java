package com.zsf;

import com.zsf.pojo.Gril;
import com.zsf.service.GrilService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: hellogril
 * @ClassName: GrilServiceTest
 * @description: GrilService  的 单元测试
 * @author: 周生锋
 * @create: 2018-11-27 19:32
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class GrilServiceTest {

    @Autowired
    private GrilService grilService;

    @Test
    public void findOneTest() {
        Gril gril = grilService.findOne(2);
        Assert.assertEquals(new Integer(22), gril.getAge());
    }


}
