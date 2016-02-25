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
package com.johnson.axes.blade.common;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Johnson.Liu
 * <p/>
 * Author: Johnson.Liu
 * Date: 2014/03/06
 * Time: 16:16
 */
public class Generic {

    public static <T> List<T> newList() {
        return new ArrayList<T>();
    }

    public static void main(String[] args) {
        List<String> names = Generic.newList();
        names.add("ok");
        System.out.println(names);
    }
}
