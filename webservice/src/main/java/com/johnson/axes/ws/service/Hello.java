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
package com.johnson.axes.ws.service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

/**
 * Created by Johnson.Liu
 * <p/>
 * Author: Johnson.Liu
 * Date: 2013/10/11
 * Time: 15:42
 */
@WebService
public class Hello {

    @WebMethod
    public String hello(String name) {
        return "Hello, " + name;
    }

    public static void main(String[] args) {
        Hello hello = new Hello();
        /*
         * 1. 发布一个webservice,发布之后可以访问
         * http://localhost:8080/hello?wsdl
         * 该文件即webservice的描述文件
         *
         * 2.命令行下使用
         * wsimport -p com.johnson.axes.ws.proxy -keep http://localhost:8080/hello?wsdl
         * 可以在命令行的当前目录下生成客户端代理类库。如果提示找不到命令，需要把%JAVA_HOME%\bin 配置到环境变量path中去，或者直接去java安装目录下的bin目录里找此命令。
         * 注意：先cd 到一个指定的空目录下再执行这个命令，否则会把代码生成到系统默认目录。
         *
         * 3.编写com.johnson.axes.ws.client.HelloClient类调用该服务
         */
        Endpoint endpoint = Endpoint.publish("http://localhost:8080/hello", hello);

    }
}
