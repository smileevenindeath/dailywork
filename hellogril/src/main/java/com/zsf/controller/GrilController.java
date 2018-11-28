package com.zsf.controller;

import com.zsf.pojo.Gril;
import com.zsf.pojo.Result;
import com.zsf.repository.GrilRepository;
import com.zsf.service.GrilService;
import com.zsf.utils.MsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @program: hellogril
 * @ClassName: GrilController
 * @description: controller
 * @author: 周生锋
 * @create: 2018-11-27 13:59
 **/
@RestController
public class GrilController {

    @Autowired
    private GrilRepository grilRepository;

    /**
     * @Description: 查询所有女生列表
     * @Param: []
     * @return: java.util.List<com.zsf.hellogril.Gril>
     * @Author: ZhouShengfeng
     * @Date: 2018/11/27
     */
    @GetMapping(value = "/grils")
    public List<Gril> grilList() {
        return grilRepository.findAll();
    }

    /**
     * 0
     *
     * @Description: 当参数很多时使用对象传递信息 在对象属性添加注解 @MIN()  使用BindingResult 接收返回信息
     * @Param: [gril, bindingResult]
     * @return: Gril
     * @Author: ZhouShengfeng
     * @Date: 2018/11/27
     */
    @PostMapping(value = "grils")
    public Result<Gril> grilAdd(@Valid Gril gril, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String msg = bindingResult.getFieldError().getDefaultMessage();
            return MsgUtil.error(200, msg);
        }
        gril.setCupSize(gril.getCupSize());
        gril.setAge(gril.getAge());
        return MsgUtil.success(grilRepository.save(gril));
    }

    /**
     * @Description: 添加一个女生信息
     * @Param: [cupSize, age]
     * @return: com.zsf.hellogril.Gril
     * @Author: ZhouShengfeng
     * @Date: 2018/11/27
     */
    /*public Gril grilAdd(@RequestParam("cupSize") String cupSize,
                        @RequestParam("age") Integer age) {
        Gril gril = new Gril();
        gril.setCupSize(cupSize);
        gril.setAge(age);
        return grilRepository.save(gril);
    }*/

    /**
     * @Description: 查找一个女生 通过id
     * @Param: [id]
     * @return: com.zsf.hellogril.Gril
     * @Author: ZhouShengfeng
     * @Date: 2018/11/27
     */
    @GetMapping(value = "grils/{id}")
    public Gril grilFindOne(@PathVariable("id") Integer id) {
        return grilRepository.findOne(id);

    }

    /**
     * @Description: 根据id 更新一条记录
     * @Param: [id, cupSize, age]
     * @return: com.zsf.hellogril.Gril
     * @Author: ZhouShengfeng
     * @Date: 2018/11/27
     */
    @PutMapping(value = "grils/{id}")
    public Gril grilUpdate(@PathVariable("id") Integer id,
                           @RequestParam("cupSize") String cupSize,
                           @RequestParam("age") Integer age) {
        Gril gril = new Gril();
        gril.setId(id);
        gril.setCupSize(cupSize);
        gril.setAge(age);
        return grilRepository.save(gril);
    }

    /**
     * @Description: 根据id 删除  一条记录
     * @Param: [id]
     * @return: void
     * @Author: ZhouShengfeng
     * @Date: 2018/11/27
     */
    @DeleteMapping(value = "grils/{id}")
    public void grilRemove(@PathVariable("id") Integer id) {
        grilRepository.delete(id);
    }

    /**
     * @Description: 通过年龄查询记录
     * @Param: [age]
     * @return: java.util.List<com.zsf.hellogril.Gril>
     * @Author: ZhouShengfeng
     * @Date: 2018/11/27
     */
    @GetMapping(value = "grils/age/{age}")
    public List<Gril> grilListByAge(@PathVariable("age") Integer age) {
        return grilRepository.findByAge(age);
    }

    @Autowired
    private GrilService grilService;

    /**
     * @Description: 调用 grilService  插入两条记录 验证事务
     * @Param: []
     * @return: void
     * @Author: ZhouShengfeng
     * @Date: 2018/11/27
     */
    @PostMapping(value = "/grils/two")
    public void grilsTwoNew() {
        grilService.insertTwoGril();
    }

    @GetMapping(value = "grils/getAge/{id}")
    public void getAge(@PathVariable("id") Integer id) throws Exception {
        grilService.getAge(id);
    }

}
