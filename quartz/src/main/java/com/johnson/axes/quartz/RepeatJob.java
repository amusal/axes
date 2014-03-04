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
 * Date: 2013/11/13
 * Time: 16:28
 */
public class RepeatJob implements Job{

    Logger logger = Logger.getLogger(RepeatJob.class.getName());

    long sleep = 5 * 1000;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobKey key = context.getJobDetail().getKey();
        logger.info(key.getName() + " sleep " + sleep / 1000L + "seconds...");
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info(key.getName() + " gets up.");
    }

    public void run() throws SchedulerException {
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler scheduler = sf.getScheduler();
        JobDetail job = JobBuilder.newJob(RepeatJob.class).withIdentity("sleepJob1", "group1").build();
        SimpleTrigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("sleepTrigger1", "group1")
                .withSchedule(SimpleScheduleBuilder
                        .simpleSchedule()
                        .withIntervalInSeconds(4)
                        .repeatForever()
                )
                .startAt(new Date(new Date().getTime() + 1000))
                .build();
        Date date = scheduler.scheduleJob(job, trigger);
        logger.info(job.getKey() + " will start at " + date + " and repeat "
                + trigger.getRepeatCount() + " by " + trigger.getRepeatInterval());

        scheduler.start();
        logger.info("RepeatJob starts.");
        try {
            Thread.sleep(20 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        scheduler.shutdown(true);
    }

    public static void main(String[] args) {
        try {
            new RepeatJob().run();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
