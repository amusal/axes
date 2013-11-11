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
package com.johnson.axes.jsoup;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by Johnson.Liu
 * <p/>
 * Author: Johnson.Liu
 * Date: 2013/10/09
 * Time: 16:25
 */
public class JsoupUtil {

    /**
     * Get document by url
     * @param url
     * @return
     * @throws IOException
     */
    public static Document getDocument(String url) throws IOException {
        Connection conn = Jsoup.connect(url);
        conn.timeout(10000);
        conn.header("Accept-Encoding", "gzip,deflate,sdch");
        conn.header("Connection", "close");
        conn.header("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US)" +
                " AppleWebKit/525.13 (KHTML, like Gecko) Chrome/0.2.149.29 Safari/525.13");
        Document doc = conn.get();
        return doc;
    }

    private static final String URL = "http://movie.douban.com/subject/5996801/";
    public static void main(String[] args) throws IOException {
        Document doc = getDocument(URL);
        Elements elements = doc.select("body > div#wrapper > div#content > div.grid-16-8 > div.article > div.indent > div.subjectwrap > div.subject > div#info > span.pl:contains(语言)");
        for (Element elem : elements) {
            String text = elem.text();
            System.out.println("text： " + text);
        }
    }
}
