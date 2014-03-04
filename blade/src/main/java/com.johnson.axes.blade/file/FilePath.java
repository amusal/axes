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
package com.johnson.axes.blade.file;

import java.io.File;

/**
 * Created by Johnson.Liu
 * <p/>
 * Author: Johnson.Liu
 * Date: 2013/11/28
 * Time: 09:47
 */
public class FilePath {

    public static void main(String[] args) {
        System.out.println("Thread.currentThread().getContextClassLoader().getResource()");
        System.out.println(Thread.currentThread().getContextClassLoader().getResource("123.txt"));
        System.out.println(Thread.currentThread().getContextClassLoader().getResource("/123.txt"));

        System.out.println("\nFilePath.class.getClassLoader().getResource()");
        System.out.println(FilePath.class.getClassLoader().getResource("123.txt"));
        System.out.println(FilePath.class.getClassLoader().getResource("/123.txt"));

        System.out.println("\nFilePath.class.getResource()");
        System.out.println(FilePath.class.getResource("123.txt"));
        System.out.println(FilePath.class.getResource("/123.txt"));

        System.out.println("\nClassLoader.getSystemResource()");
        System.out.println(ClassLoader.getSystemResource("123.txt"));
        System.out.println(ClassLoader.getSystemResource("/123.txt"));

        System.out.println("\nnew File(\"\").getAbsolutePath()");
        System.out.println(new File("/123.txt").getAbsolutePath());
        System.out.println(new File("123.txt").getAbsolutePath());

        System.out.println("\nuser.dir");
        System.out.println(System.getProperty("user.dir"));
    }
}
