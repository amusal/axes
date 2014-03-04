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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Johnson.Liu
 * <p/>
 * Author: Johnson.Liu
 * Date: 2013/11/27
 * Time: 16:48
 */
@Configuration
public class AppConfig {

//    @Value("#{jdbcProperties.url}")
    private String jdbcUrl;

//    @Value("#{jdbcProperties.username}")
    private String username;

//    @Value("#{jdbcProperties.password}")
    private String password;

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    @Value("${url}")
    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getUsername() {
        return username;
    }

    @Value("#{jdbc.username}")
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    @Value ("#{jdbc.password}")
    public void setPassword(String password) {
        this.password = password;
    }

    @Bean
    public DataSource dataSource() {
        return null;
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        FooComponent foo = context.getBean(FooComponent.class);
        foo.display();
    }

    @Repository
    public static class FooComponent {

        @Autowired
        private DataSource ds;

        public void display() {
            System.out.println("ds: " + ds);
        }
    }

//    public static class DriverManagerDataSource implements DataSource {
//
//        public DriverManagerDataSource(String url, String username, String password) {
//            System.out.println(url + ", " + username + ", " + password);
//        }
//
//        @Override
//        public Connection getConnection() throws SQLException {
//            return null;  //To change body of implemented methods use File | Settings | File Templates.
//        }
//
//        @Override
//        public Connection getConnection(String username, String password) throws SQLException {
//            return null;  //To change body of implemented methods use File | Settings | File Templates.
//        }
//
//        @Override
//        public PrintWriter getLogWriter() throws SQLException {
//            return null;  //To change body of implemented methods use File | Settings | File Templates.
//        }
//
//        @Override
//        public void setLogWriter(PrintWriter out) throws SQLException {
//            //To change body of implemented methods use File | Settings | File Templates.
//        }
//
//        @Override
//        public void setLoginTimeout(int seconds) throws SQLException {
//            //To change body of implemented methods use File | Settings | File Templates.
//        }
//
//        @Override
//        public int getLoginTimeout() throws SQLException {
//            return 0;  //To change body of implemented methods use File | Settings | File Templates.
//        }
//
//        @Override
//        public <T> T unwrap(Class<T> iface) throws SQLException {
//            return null;  //To change body of implemented methods use File | Settings | File Templates.
//        }
//
//        @Override
//        public boolean isWrapperFor(Class<?> iface) throws SQLException {
//            return false;  //To change body of implemented methods use File | Settings | File Templates.
//        }
//    }

}
