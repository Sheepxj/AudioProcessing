package util;

import java.io.*;
import java.util.Arrays;

import org.apache.commons.io.IOUtils;

public class WavToPcm {
    public static void main(String[] args) {
        String s = convertAudioFiles("J:\\CloudMusic\\还在流浪改.wav");
        System.out.printf(s);
    }



    /**
     * WAV转PCM文件
     * @param wavfilepath wav文件路径
     * @return
     */
    public static String convertAudioFiles(String wavfilepath){
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream;
        try{
            fileInputStream = new FileInputStream(wavfilepath);
            byte[] wavbyte = InputStreamToByte(fileInputStream);//获取二进制数组

            File file = new File(wavfilepath);
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("");// 清空
            fileWriter.flush();
            fileWriter.close();
            System.out.println("原文件已清空");

            fileOutputStream = new FileOutputStream(wavfilepath);
            byte[] pcmbyte = Arrays.copyOfRange(wavbyte, 44, wavbyte.length);
            fileOutputStream.write(pcmbyte);

            IOUtils.closeQuietly(fileInputStream);
            IOUtils.closeQuietly(fileOutputStream);//关闭输入输出流
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return wavfilepath;
    }
    /**
     * 输入流转byte二进制数据
     * @param fis
     * @return
     * @throws IOException
     */
    private static byte[] InputStreamToByte(FileInputStream fis) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        long size = fis.getChannel().size();
        byte[] buffer = null;
        if (size <= Integer.MAX_VALUE) {
            buffer = new byte[(int) size];
        } else {
            buffer = new byte[8];
            for (int ix = 0; ix < 8; ++ix) {
                int offset = 64 - (ix + 1) * 8;
                buffer[ix] = (byte) ((size >> offset) & 0xff);
            }
        }
        int len;
        while ((len = fis.read(buffer)) != -1) {
            byteStream.write(buffer, 0, len);
        }
        byte[] data = byteStream.toByteArray();
        IOUtils.closeQuietly(byteStream);
        return data;
    }
}