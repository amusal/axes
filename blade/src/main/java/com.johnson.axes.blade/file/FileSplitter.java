package com.johnson.axes.blade.file;

import java.io.*;

/**
 * FileSplitter is a tool to split big file to small files.
 */
public class FileSplitter {

    public static void splitFile(String srcFile, String destinationDir, SplitConfig config) throws IOException {
        // input checking
        File file = new File(srcFile);
        if (!file.exists() || !file.isFile()) {
            throw new RuntimeException("File " + srcFile + " not found.");
        }
        File destDir = new File(destinationDir);
        if (destDir.exists() && !destDir.isDirectory()) {
            throw new RuntimeException(destinationDir + " is not a directory.");
        }
        if (!destDir.exists()) {
            destDir.mkdirs();
        }
        // split
        long size = 100 * 1024 * 1024;
        String name = getFileName(srcFile);
        BufferedReader br = new BufferedReader(new FileReader(file));
        long counter = 0L;
        String line;

        String newFileName = destinationDir + File.separator + generateNewFileName(name, "001");
        createNewFile(newFileName);
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(newFileName)));
        long s = System.currentTimeMillis();
        while ((line=br.readLine()) != null && counter < size) {
            writer.write(line + "\n");
            counter += line.getBytes().length;
        }
        long e = System.currentTimeMillis();
        System.out.println("lenth: " + size + "");
        System.out.println("time: " + (e - s) + "ms.");
        br.close();
        writer.close();
    }

    /**
     * Create a new file, do nothing if file already exists.
     *
     * @param fileName
     * @throws IOException
     */
    public static void createNewFile(String fileName) throws IOException {
        File file = new File(fileName);
        if (file.exists() && file.isDirectory()) {
            throw new RuntimeException(fileName + " is a directory!");
        }
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    /**
     * Split a file name with suffix to an array.
     * For example, name "axes.pdf" will be split as {"axes", "pdf"}.
     * If no '.' be found, the method returns fileName as {fileName}.
     *
     * @param fileName
     * @return
     */
    public static String[] splitFileName(String fileName) {
        int dotIdx = fileName.lastIndexOf('.');
        if (dotIdx == -1) {
            return new String[] {fileName};
        } else {
            return new String[] {fileName.substring(0, dotIdx), fileName.substring(dotIdx+1)};
        }
    }

    /**
     * Get the file name from a full file name with path.
     * Etc, a full file name "d:\\full.pdf" returns a string "full.pdf".
     *
     * @param fullFileName
     * @return
     */
    public static String getFileName(String fullFileName) {
        int sepIdx = fullFileName.lastIndexOf(File.separator);
        if (sepIdx == -1) {
            return fullFileName;
        } else {
            return fullFileName.substring(sepIdx+1);
        }
    }

    /**
     * Use appendStr to generate a new file name.
     * For example, if originName is "origin.pdf", appendStr is "001",
     * result will be "origin001.pdf".
     *
     * @param originName
     * @param appendStr
     * @return
     */
    public static String generateNewFileName(String originName, String appendStr) {
        String[] fileNames = splitFileName(originName);
        if (fileNames.length == 1) {
            return fileNames[0] + appendStr;
        }
        return fileNames[0] + appendStr + "." + fileNames[1];
    }

    public static class SplitConfig {
        long size;
        String name;
        String startPointAppendStr;
        public long getSize() {
            return size;
        }
        public String getName() {
            return name;
        }
    }

    public static void main(String[] args) {
        String srcFile = "D:\\temp\\2013-09-02-0000-2330_hot.media.ysten.com.cn.log";
        String destDir = "D:\\temp\\w3c";
        try {
            splitFile(srcFile, destDir, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}