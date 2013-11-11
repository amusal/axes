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
package com.johnson.axes.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Johnson.Liu
 * <p/>
 * Author: Johnson.Liu
 * Date: 2013/10/18
 * Time: 15:34
 */
public class Regex {

    public static void main(String[] args) {
//        String patternStr = "(.+)|(.+)第\\d+集";
//        String patternStr = "(.+)第\\d+集|(.+)";
//        String patternStr = "(.+)";
        String patternStr = "((\\d{3,5})([a-z]{2}))\\-?";
        Pattern pattern = Pattern.compile(patternStr);
//        String src = "印 三生石 第3集";
        String src = "132aa-3423bb-23153cc-00-abc-0000bcd";
        Matcher matcher = pattern.matcher(src);
        while (matcher.find()) {
            int groupCount = matcher.groupCount();
            System.out.println(groupCount);
            for (int i=0; i<=groupCount; i++) {
                String group = matcher.group(i);
                System.out.println(group);
            }
        }
    }
}
