/*
 * Copyright 2013 Future TV, Inc.
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
package com.johnson.axes.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;
import java.util.logging.Logger;

/**
 * Created by Johnson.Liu
 * <p/>
 * Author: Johnson.Liu
 * Date: 2013/11/11
 * Time: 17:34
 */
public class HelloJob implements Job {
    private static Logger logger = Logger.getLogger(HelloJob.class.getName());

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobKey jobKey = context.getJobDetail().getKey();
        logger.info("Hello, Job!-" + new Date());
        logger.info("HelloJob says: " + jobKey + " executing at " + new Date());
    }

    public void run() throws SchedulerException {
        logger.info("------Initial-------");

        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler scheduler = sf.getScheduler();
        logger.info("------Initialization complete-------");

        Date runtime = DateBuilder.evenMinuteDate(new Date());
        JobDetail job = JobBuilder.newJob(HelloJob.class)
                .withIdentity("helloJob", "group1")
                .build();
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("hellotrigger", "group1")
                .startAt(runtime)
                .build();
        scheduler.scheduleJob(job, trigger);
        logger.info(job.getKey() + " will run at: " + runtime);

        scheduler.start();
        logger.info("------------Started scheduler------------");
        logger.info("------------Wait 65 seconds---------------");
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info("-------------Shutting down----------------");
        scheduler.shutdown(true);
        logger.info("-----------Shutdown complete--------------");
    }

    public static void main(String[] args) {
        HelloJob hello = new HelloJob();
        try {
            hello.run();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
