package com.segmentfault.springbootlesson11;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 62667
 */
@SpringBootApplication
//@MapperScan("com.segmentfault.springbootlesson11.mapper")
public class SpringBootLesson11Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootLesson11Application.class, args);
	}

}
