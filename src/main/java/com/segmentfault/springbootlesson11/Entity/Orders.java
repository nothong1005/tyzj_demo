package com.segmentfault.springbootlesson11.Entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author 62667
 */

@Entity
@ApiModel
public class Orders implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "订单id")
    private Long id;

    @Column(length = 255)
    @ApiModelProperty(value = "订单状态")
    private String state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", state='" + state + '\'' +
                '}';
    }
}
