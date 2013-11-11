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

import org.mozilla.intl.chardet.nsDetector;
import org.mozilla.intl.chardet.nsICharsetDetectionObserver;
import org.mozilla.intl.chardet.nsPSMDetector;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Use as follow:
 *      String[] charset = new CharsetDetector().detectChineseCharset(in);
 * If detector can't detect a charset exactly, it returns a string array contains all probable charset.
 * Usually the first element is the most probable charset.
 *
 * Created by Johnson.Liu
 * <p/>
 * Author: Johnson.Liu
 * Date: 2013/10/10
 * Time: 11:26
 */
public class CharsetDetector {

    public static final byte[] UTF8_BOM = new byte[] {-17, -69, -65};
    public static final String CHARSET_UTF8 = "UTF-8";
    public static final String CHARSET_ISO_8859_1 = "ISO-8859-1";

    private boolean found = false;
    private String charset;

    /**
     * Detect with language priority.
     * Lang option refers to static final field of nsPSMDetector, such as
     * nsPSMDetector.CHINESE, nsPSMDetector.ALL, etc.
     *
     * @param in
     * @param lang
     * @return
     * @throws IOException
     */
    public String[] detectCharset(InputStream in, int lang) throws IOException {
        // Detect utf8 with boom
        BufferedInputStream bufferedInputStream = new BufferedInputStream(in);
        byte[] buf = new byte[1024];
        int len = bufferedInputStream.read(buf, 0, buf.length);
        if (isUTF8WithBom(buf)) {
            bufferedInputStream.close();
            return new String[] {CHARSET_UTF8};
        }
        // Other detect by nsDetector
        nsDetector detector = new nsDetector(lang);
        detector.Init(new nsICharsetDetectionObserver() {
            @Override
            public void Notify(String charset) {
                CharsetDetector.this.found = true;
                CharsetDetector.this.charset = charset;
            }
        });
        boolean isAscii = true;
        while (len != -1) {
            if (isAscii) {
                isAscii = detector.isAscii(buf, len);
            }
            if (!isAscii) {
                if (detector.DoIt(buf, len, false)) {
                    break;
                }
            }
            len = bufferedInputStream.read(buf, 0, buf.length);
        }
        detector.DataEnd();
        bufferedInputStream.close();
        if (isAscii) {
            return new String[] {CHARSET_ISO_8859_1};
        } else if (found) {
            return new String[] {charset};
        } else {
            return detector.getProbableCharsets();
        }
    }

    /**
     * Universal character charset detect
     * @param in
     * @return
     * @throws IOException
     */
    public String[] detectAllCharset(InputStream in) throws IOException {
        return detectCharset(in, nsPSMDetector.ALL);
    }

    /**
     * Detect and Chinese character has priority
     * @param in
     * @return
     * @throws IOException
     */
    public String[] detectChineseCharset(InputStream in) throws IOException {
        return detectCharset(in, nsPSMDetector.CHINESE);
    }

    /**
     * Fast detect UTF8 with bom mark
     * This is a pure method, that means the InputStream won't change after method runs out.
     *
     * @param bytes
     * @return
     * @throws IOException
     */
    public boolean isUTF8WithBom(byte[] bytes) throws IOException {
        if (bytes.length < UTF8_BOM.length) {
            return false;
        }
        for (int i=0; i<UTF8_BOM.length; i++) {
            if (bytes[i] != UTF8_BOM[i]) {
                return false;
            }
        }
        return true;
    }
}
