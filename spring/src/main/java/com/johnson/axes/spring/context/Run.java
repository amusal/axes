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

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Johnson.Liu
 * <p/>
 * Author: Johnson.Liu
 * Date: 2013/11/27
 * Time: 16:02
 */
@Configuration
@ComponentScan
public class Run {

    @Bean(name="simpleService")
    public SimpleService getSimpleService() {
        return new SimpleServiceImpl();
    }

    @Bean(name="dynamicSimpleService")
    public SimpleService getDynamicSimpleService() {
        return new SimpleService() {
            @Override
            public String getName() {
                return "dynamic spring";
            }
        };
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Run.class);
        SimpleComponent component = context.getBean(SimpleComponent.class);
        component.run();
    }
}
