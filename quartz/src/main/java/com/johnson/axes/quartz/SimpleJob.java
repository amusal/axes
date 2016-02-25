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
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.logging.Logger;

/**
 * Created by Johnson.Liu
 * <p/>
 * Author: Johnson.Liu
 * Date: 2013/11/12
 * Time: 14:49
 */
public class SimpleJob implements Job {

    Logger logger = Logger.getLogger(Job.class.getName());

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobKey jobKey = context.getJobDetail().getKey();
        logger.info("SimpleJob says: " + jobKey + " executeing at " + new Date());
    }

    public void run() throws SchedulerException {
        logger.info("-------initial---------------");
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler scheduler = sf.getScheduler();
        logger.info("--------initial complete---------");

        logger.info("----------schedule jobs-------------");
        Date startTime = DateBuilder.nextGivenSecondDate(null, 15);
        JobDetail job = JobBuilder.newJob(SimpleJob.class)
                .withIdentity("job1", "group1")
                .build();
        SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .startAt(startTime)
                .build();
        Date ft = scheduler.scheduleJob(job, trigger);
        logger.info(job.getKey() + " will run at: " + ft
                + " and repeat: " + trigger.getRepeatCount()
                + " times, every " + trigger.getRepeatInterval() / 1000L + " seconds");

        job = JobBuilder.newJob(SimpleJob.class).withIdentity("job2", "group1").build();
        trigger = (SimpleTrigger) TriggerBuilder
                .newTrigger()
                .withIdentity("trigger2", "group1")
                .startAt(startTime)
                .build();
        ft = scheduler.scheduleJob(job, trigger);
        logger.info(job.getKey() + " will run at: " + ft
                + " and repeat: " + trigger.getRepeatCount()
                + ", every " + trigger.getRepeatInterval() / 1000L + " seconds");

        job = JobBuilder.newJob(SimpleJob.class).withIdentity("job3", "group1").build();
        trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("trigger3", "group1")
                .startAt(startTime)
                .withSchedule(
                        SimpleScheduleBuilder
                                .simpleSchedule()
                                .withIntervalInSeconds(10)
                                .withRepeatCount(10))
                .build();
        ft = scheduler.scheduleJob(job, trigger);
        logger.info(job.getKey() + " will run at: " + ft + " and repeat: "
                + trigger.getRepeatCount() + " times, every "
                + trigger.getRepeatInterval() / 1000L + " seconds");

        job = JobBuilder.newJob(SimpleJob.class).withIdentity("job3", "group2").build();
        trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("trigger3", "group2")
                .startAt(startTime)
                .withSchedule(
                        SimpleScheduleBuilder
                                .simpleSchedule()
                                .withIntervalInSeconds(10)
                                .withRepeatCount(20))
                .forJob(job)
                .build();
        ft = scheduler.scheduleJob(job, trigger);
        logger.info(job.getKey() + " will [also] run at: " + ft
                + " and repeat: " + trigger.getRepeatCount()
                + " times, every " + trigger.getRepeatInterval() / 1000L + " seconds");

        job = JobBuilder.newJob(SimpleJob.class).withIdentity("job4", "group1").build();
        trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("trigger4", "group1")
                .startAt(startTime)
                .withSchedule(
                        SimpleScheduleBuilder
                                .simpleSchedule()
                                .withIntervalInSeconds(10)
                                .withRepeatCount(5))
                .build();
        ft = scheduler.scheduleJob(job, trigger);
        logger.info(job.getKey() + " will run at: " + ft + " and repeat: "
                + trigger.getRepeatCount() + " times, every "
                + trigger.getRepeatInterval() / 1000L + " seconds");

        job = JobBuilder.newJob(SimpleJob.class).withIdentity("job6", "group1").build();
        trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("trigger6", "group1")
                .startAt(startTime)
                .withSchedule(SimpleScheduleBuilder
                        .simpleSchedule()
                        .withIntervalInSeconds(40)
                        .repeatForever()
                )
                .build();
        ft = scheduler.scheduleJob(job, trigger);
        logger.info(job.getKey() + " will run at: " + ft + " and repeat: "
                + trigger.getRepeatCount() + " times, every "
                + trigger.getRepeatInterval() / 1000L + " seconds");

        logger.info("----------start scheduler----------");
        scheduler.start();
        logger.info("----------start scheduler complete------------");

        job = JobBuilder.newJob(SimpleJob.class).withIdentity("job7", "group1").build();
        trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("trigger7", "group1")
                .startAt(startTime)
                .withSchedule(SimpleScheduleBuilder
                        .simpleSchedule()
                        .withIntervalInMinutes(5).withRepeatCount(20)
                )
                .build();
        ft = scheduler.scheduleJob(job, trigger);
        logger.info(job.getKey() + " will run at: " + ft
                + " and repeat: " + trigger.getRepeatCount()
                + " times, every " + trigger.getRepeatInterval() / 1000L + " seconds");

        job = JobBuilder.newJob(SimpleJob.class).withIdentity("job8", "group1").storeDurably().build();
        scheduler.addJob(job, true);
        logger.info("'Manually' triggering job...");
        scheduler.triggerJob(JobKey.jobKey("job8", "group1"));

        logger.info("----------waiting 30 seconds...--------------");

        try {
            Thread.sleep(30 * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info("-----------rescheduling-------------");
        trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("trigger7", "group1")
                .startAt(startTime)
                .withSchedule(SimpleScheduleBuilder
                        .simpleSchedule()
                        .withIntervalInMinutes(5)
                        .withRepeatCount(20)
                )
                .build();
        ft = scheduler.rescheduleJob(trigger.getKey(), trigger);
        logger.info("job7 rescheduled to run at: " + ft);

        logger.info("---------waiting five minute...------------");
        try {
            Thread.sleep(5 * 60 * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("--------------shutting donw----------------");
        scheduler.shutdown(true);
        logger.info("-------------shutdown complete--------------");

        SchedulerMetaData metaData = scheduler.getMetaData();
        logger.info("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");
    }

    public static void main(String[] args) throws SchedulerException {
        SimpleJob job = new SimpleJob();
        job.run();
    }
}
