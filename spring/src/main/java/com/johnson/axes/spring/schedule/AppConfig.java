/*
 * Copyright 2014 Future TV, Inc.
 *
 *      Licensed under the Apache License, Version 2.0 (the "License");
 *      you may not use this file except in compliance with the License.
 *      You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *      Unless required by applicable law or agreed to in writing, software
 *      distributed under the License is distributed on an "AS IS" BASIS,
 *      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *      See the License for the specific language governing permissions and
 *      limitations under the License.
 */
package com.johnson.axes.spring.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by Johnson.Liu
 * <p/>
 * Author: Johnson.Liu
 * Date: 2014/01/07
 * Time: 16:01
 */
@Configuration (value="com.johnson.axes.spring.schedule.appConfig")
@EnableScheduling
public class AppConfig implements SchedulingConfigurer {

//    @Bean
//    public SimpleJob getSimpleJob() {
//        return new SimpleJob();
//    }

    @Autowired
    private SimpleJob simpleJob;

    @Scheduled (fixedRate = 2000L)
    public void doJob() {
        simpleJob.doJob();
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskScheduler2());
    }

    @Bean (destroyMethod = "shutdown")
    public ScheduledExecutorService taskScheduler2() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(10);
        return executor;
    }
}
