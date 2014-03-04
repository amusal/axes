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
package com.johnson.axes.spring.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by Johnson.Liu
 * <p/>
 * Author: Johnson.Liu
 * Date: 2013/12/03
 * Time: 09:24
 */
@Component
@ComponentScan
public class QualifiedSimpleComponent {

    @Autowired
    @ServiceQualifier(name = "qualified", delaly = 3)
    private SimpleService simpleService;

    @Autowired
    private SimpleService dynamicSimpleService;

    @Resource
    private ApplicationContext context;

    public void run() {
        System.out.println("hello, " + simpleService.getName());
        QualifiedSimpleComponent component = context.getBean(QualifiedSimpleComponent.class);
        System.out.println("component == this ? " + (component == this));

        System.out.println("hello, " + dynamicSimpleService.getName());
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        QualifiedSimpleComponent component = context.getBean(QualifiedSimpleComponent.class);
        component.run();


    }

    @Bean
    @ServiceQualifier(name="qualified", delaly = 3)
    public SimpleService getService() {
        return new SimpleService() {
            @Override
            public String getName() {
                return this.getClass().getName();
            }
        };
    }
}
