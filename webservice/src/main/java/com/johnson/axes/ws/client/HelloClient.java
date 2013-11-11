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
package com.johnson.axes.ws.client;

import com.johnson.axes.ws.proxy.Hello;
import com.johnson.axes.ws.proxy.HelloService;

/**
 * Created by Johnson.Liu
 * <p/>
 * Author: Johnson.Liu
 * Date: 2013/10/11
 * Time: 15:58
 */
public class HelloClient {

    public static void main(String[] args) {
        HelloService service = new HelloService();
        // 注意： 这里的Hello是使用命令生成的类，位于proxy下，而不是service包下的Hello类
        Hello proxy = service.getHelloPort();
        String hello = proxy.hello("Johnson");
        System.out.println(hello);
    }
}
