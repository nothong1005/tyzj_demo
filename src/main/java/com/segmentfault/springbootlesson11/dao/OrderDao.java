package com.segmentfault.springbootlesson11.dao;

import com.segmentfault.springbootlesson11.domain.Orders;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 62667
 */

@Component
public class OrderDao {

    private final SqlSessionTemplate sqlSessionTemplate;


    public OrderDao(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    public List<Orders> selectByState(String state) {
        return this.sqlSessionTemplate.selectList(state);
    }

}
