package com.segmentfault.springbootlesson11.config;

import com.segmentfault.springbootlesson11.job.UpdateOrderJob;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.List;

/**
 * @author 62667
 */
@Configuration
public class QuartzConfiguration  {

    private Logger LOGGER = LoggerFactory.getLogger(QuartzConfiguration.class);

    @Bean(name="orderUpdateJob")
    MethodInvokingJobDetailFactoryBean orderUpdateJobDetail(UpdateOrderJob task) {

        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        /*
         * 是否并发执行 例如每5s执行一次任务，但是当前任务还没有执行完，就已经过了5s了，
         * 如果此处为true，则下一个任务会执行，如果此处为false，则下一个任务会等待上一个任务执行完后，再开始执行
         */
        jobDetail.setConcurrent(false);
        jobDetail.setName("orderUpdateJob");
        jobDetail.setGroup("duan");
        // 这些属性都可以存储在数据库中，在多任务的时候使用

        /*
         * 为需要执行的实体类对应的对象
         */
        jobDetail.setTargetObject(task);

        /*
         * syncWorkOrderFile10M为需要执行的方法
         * 通过这几个配置，告诉JobDetailFactoryBean我们需要执行定时执行SyncWorkOrderFileTask类中的syncWorkOrderFile10M方法
         */
        jobDetail.setTargetMethod("execute");

        return jobDetail;
    }
    /**
     * attention: Details：配置定时任务的触发器，也就是什么时候触发执行定时任务
     */
    @Bean(name = "orderUpdateJobTrigger")
    public CronTriggerFactoryBean orderJobTriggerFactoryBean(JobDetail orderUpdateJobDetail) {

        CronTriggerFactoryBean jobTrigger = new CronTriggerFactoryBean();
        /*
         * 为需要执行的定时任务
         */
        jobTrigger.setJobDetail(orderUpdateJobDetail);
        jobTrigger.setCronExpression("* /2 * * * ?");
        jobTrigger.setName("orderUpdateTrigger");
        LOGGER.info("定时触发器");
        return jobTrigger;

    }

    /**
     * attention: Details：定义quartz调度工厂
     * 这里的参数为List，Spring会把上面声明的触发器都放到这个集合中
     */
    @Bean(name = "scheduler")
    public SchedulerFactoryBean schedulerFactory(List<Trigger> triggers) {

        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        // 用于quartz集群,QuartzScheduler 启动时更新己存在的Job
        bean.setOverwriteExistingJobs(true);
        // 延时启动，应用启动1秒后
        bean.setStartupDelay(1);
        /*
         * 注册触发器
         * 这里的setTriggers()方法，参数可以为多个Trigger，
         * 所以将配置的每个触发器放入里面即可(但当触发器多时，这里的参数就会很多，目前没找到更好的方式)
         */
        bean.setTriggers(triggers.get(0));
        LOGGER.info("调度工厂配置完成,Quartz在应用启动1秒后启动");
        return bean;

    }
}
