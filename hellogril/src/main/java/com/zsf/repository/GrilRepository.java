package com.zsf.repository;

import com.zsf.pojo.Gril;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: hellogril
 * @ClassName: GrilRepository
 * @description: Gril datasource接口
 * @author: 周生锋
 * @create: 2018-11-27 14:02
 **/
@Repository
public interface GrilRepository extends JpaRepository<Gril, Integer> {

    /**
     * @Description: 扩展 通过年龄查询信息 fingByAge 函数名称必须遵循规范
     * @Param: [age]
     * @return: java.util.List<com.zsf.hellogril.Gril>
     * @Author: ZhouShengfeng
     * @Date: 2018/11/27
     */
    public List<Gril> findByAge(Integer age);
}
