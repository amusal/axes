/*
 * Copyright 2016 Future TV, Inc.
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
package com.johnson.axes.logs.logback;

import com.johnson.axes.logs.logback.Corruption;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Johnson.Liu
 * <p/>
 * Author: Johnson.Liu
 * Date: 2016/02/24
 * Time: 14:30
 */
public class CorruptionTest {

    private Corruption corruption;

    @Before
    public void setup() {
        corruption = new Corruption();
    }

    @Test
    public void testCorrupt() {
        corruption.corrupt();
    }
}
