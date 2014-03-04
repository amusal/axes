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

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Johnson.Liu
 * <p/>
 * Author: Johnson.Liu
 * Date: 2013/10/10
 * Time: 10:26
 */
public class FileEncodingConverter {

    public static Map<File, Object> convert(File root, String encoding) {
        return null;
    }

    public static void convertFileToUTF8(File file) {
        // check encode

        // rename old file
        // create new file
        // delete old file
    }

    /**
     * Detect file encoding
     * @param file
     * @return
     * @throws IOException
     */
    private static String detectFileEncoding(File file) throws IOException {
        String[] charset = new CharsetDetector().detectChineseCharset(new FileInputStream(file));
        return charset[0];
    }

    /**
     * Scan all files under the root directory
     * @param root
     * @return
     */
    private static List<File> scanFiles(File root, FilenameFilter filter) {
        List<File> scannedFiles = new ArrayList<File>();
        if (root.isFile()) {
            scannedFiles.add(root);
        } else if (root.isDirectory()) {
            File[] files = root.listFiles(filter);
            for (File file : files) {
                if (file.isFile()) {
                    scannedFiles.add(file);
                } else if (file.isDirectory()) {
                    scannedFiles.addAll(scanFiles(file, filter));
                }
            }
        }
        return scannedFiles;
    }

    public static void main(String[] args) throws IOException {
        List<File> files = scanFiles(new File("D:\\workspace\\java\\fcrawl"), new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return !name.startsWith(".") && !name.endsWith(".class") && !name.equals("target");
            }
        });
        System.out.println("total: " + files.size());
        for (File file : files) {
            String encoding = detectFileEncoding(file);
            System.out.println("\t" + file.getName() + ": " + encoding);
        }
    }
}
