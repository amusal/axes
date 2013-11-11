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
package com.johnson.axes.blade;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Johnson.Liu
 * <p/>
 * Author: Johnson.Liu
 * Date: 2013/10/10
 * Time: 13:28
 */
public class CharsetDetectorTest {

    private List<String> testFiles = new ArrayList<String>();

    @Before
    public void setUp() throws Exception {
        testFiles.add("test-file-utf8.txt");
        testFiles.add("test-file-gbk.txt");
        testFiles.add("gb2312.txt");
        testFiles.add("utf8-with-boom.txt");
        testFiles.add("utf8-non-boom.txt");
        testFiles.add("gbk.txt");
        testFiles.add("big5.txt");
        testFiles.add("utf-16-le.txt");
        testFiles.add("utf8-all-en.txt");
        testFiles.add("gb2312-non-chn.txt");
    }

    @Test
    public void testDetectCharset() throws Exception {
        for (String fileName : testFiles) {
            System.out.println("----------------------------" + fileName + "-----------------------------------");
            String[] detectedChnCharsets = new CharsetDetector().detectChineseCharset(CharsetDetectorTest.class.getClassLoader().getResourceAsStream(fileName));
            printArray("detectedChnCharsets: ", detectedChnCharsets);
            Assert.assertTrue(detectedChnCharsets.length > 0);
            String[] detectedAllCharsets = new CharsetDetector().detectAllCharset(CharsetDetectorTest.class.getClassLoader().getResourceAsStream(fileName));
            printArray("detectedAllCharsets:", detectedAllCharsets);
            Assert.assertTrue(detectedAllCharsets.length > 0);
        }
    }

    private void printArray(String info, String[] array) {
        System.out.println(info);
        for (String str : array) {
            System.out.println("\t" + str);
        }
    }
}
