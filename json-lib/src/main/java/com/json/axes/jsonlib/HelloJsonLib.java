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
package com.json.axes.jsonlib;


import net.sf.json.xml.XMLSerializer;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Johnson.Liu
 * <p/>
 * Author: Johnson.Liu
 * Date: 2013/12/07
 * Time: 13:08
 */
public class HelloJsonLib {

    public static void main(String[] args) throws IOException, JSONException {
        String srcFile = "test4.xml";
        BufferedReader reader = new BufferedReader(new InputStreamReader(HelloJsonLib.class.getClassLoader().getResourceAsStream(srcFile)));
        StringBuilder strBuffer = new StringBuilder();
        String line;
        while ((line=reader.readLine()) != null) {
            strBuffer.append(line).append("\n");
        }
        String jsonStr = parseXmlByJSON(strBuffer.toString());
        System.out.println("xml: ");
        System.out.println(strBuffer.toString());
        System.out.println("-------------------json-----------------");
        System.out.println(jsonStr);
        jsonStr = parseXmlByJsonLib(strBuffer.toString());
        System.out.println("-----------------json-lib---------------------");
        System.out.println(jsonStr);
    }

    public static String parseXmlByJsonLib(String src) {
        XMLSerializer serializer = new XMLSerializer();
        serializer.setRootName("aadkljk");
        serializer.setTypeHintsEnabled(true);
        Object jsonArray = serializer.read(src);
        return jsonArray.toString();
    }

    public static String parseXmlByJSON(String src) throws JSONException {
        JSONObject json = XML.toJSONObject(src);
        String value = json.toString();
        return value;
    }


}
