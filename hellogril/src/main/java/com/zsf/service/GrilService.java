package com.zsf.service;

import com.zsf.enums.MsgCodeEnums;
import com.zsf.exception.GrilException;
import com.zsf.pojo.Gril;
import com.zsf.repository.GrilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: hellogril
 * @ClassName: GrilService
 * @description: service
 * @author: 周生锋
 * @create: 2018-11-27 14:46
 **/
@Service
public class GrilService {

    @Autowired
    private GrilRepository grilRepository;

    /**
     * @Description: 验证事务  同时插入两条信息
     * @Param: []
     * @return: void
     * @Author: ZhouShengfeng
     * @Date: 2018/11/27
     */
    @Transactional(rollbackFor = GrilException.class)   //开启事务
    public void insertTwoGril() {

        Gril grilA = new Gril();
        grilA.setCupSize("A");
        grilA.setAge(13);
        grilRepository.save(grilA);

        Gril grilB = new Gril();
        grilB.setCupSize("b");
        grilB.setAge(15);
        grilRepository.save(grilB);
    }

    /**
     * @Description: 通过id 查询年龄  对年龄进行判断 提示信息通过异常抛出
     * @Param: [id]
     * @return: void
     * @Author: ZhouShengfeng
     * @Date: 2018/11/27
     */
    public void getAge(Integer id) throws Exception {
        Gril gril = grilRepository.findOne(id);
        Integer age = gril.getAge();
        if (age < 10) {
            // 返回   呦呦呦。。。小学生
            throw new GrilException(MsgCodeEnums.PRIMARY_SCHOOL);
        } else if (age > 10 && age < 16) {
            //返回    呦呦呦。。。初中生
            throw new GrilException(MsgCodeEnums.MIDDLE_SCHOOL);
        } else {
            //返回    你已经成年了
            throw new GrilException(MsgCodeEnums.ADULT);
        }
    }

    /**
     * @Description: 通过id 查询一条记录
     * @Param: [id]
     * @return: com.zsf.pojo.Gril
     * @Author: ZhouShengfeng
     * @Date: 2018/11/27
     */
    public Gril findOne(Integer id) {
        return grilRepository.findOne(id);
    }
}
