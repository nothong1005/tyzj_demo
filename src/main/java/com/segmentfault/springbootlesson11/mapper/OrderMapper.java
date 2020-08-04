package com.segmentfault.springbootlesson11.mapper;


import com.segmentfault.springbootlesson11.domain.Orders;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * @author 62667
 */
@Mapper
public interface OrderMapper {

    @Select("select * from orders")
    @Results({
            @Result(property = "id", column = "id", jdbcType = JdbcType.VARCHAR),
            @Result(property = "state", column = "state")
    })
    List<Orders> selectAll();

    @Select("select * from orders where state=#{state}")
    @Results({
            @Result(property = "id", column = "id", jdbcType = JdbcType.VARCHAR),
            @Result(property = "state", column = "state")
    })
    List<Orders> selectByState(String state);

    @Insert("insert into orders(state) values (#{state})")
    void insert(Orders order);

}
