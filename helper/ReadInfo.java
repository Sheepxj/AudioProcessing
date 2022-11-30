package helper;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class ReadInfo {

    public static void main(String[] args) throws IOException {
        File f = new File("J:\\CloudMusic\\还在流浪格式改.wav");
        RandomAccessFile rdf = null;
        rdf = new RandomAccessFile(f,"r");

        System.out.println("声音尺寸: " + toInt(read(rdf, 4, 4))); // 声音尺寸

        System.out.println("音频格式: " + toShort(read(rdf, 20, 2))); // 音频格式 1 = PCM

        System.out.println("声道数: " + toShort(read(rdf, 22, 2))); // 1 单声道 2 双声道

        System.out.println("采样率: " + toInt(read(rdf, 24, 4)));  // 采样率、音频采样级别 8000 = 8KHz

        System.out.println("波形的数据量: " + toInt(read(rdf, 28, 4)));  // 每秒波形的数据量

        System.out.println("采样帧: " + toShort(read(rdf, 32, 2)));  // 采样帧的大小

        System.out.println("采样位数: " + toShort(read(rdf, 34, 2)));  // 采样位数

        rdf.close();

    }


    public static int toInt(byte[] b) {
        return ((b[3] << 24) + (b[2] << 16) + (b[1] << 8) + (b[0] << 0));
    }

    public static short toShort(byte[] b) {
        return (short)((b[1] << 8) + (b[0] << 0));
    }


    public static byte[] read(RandomAccessFile rdf, int pos, int length) throws IOException {
        rdf.seek(pos);
        byte result[] = new byte[length];
        for (int i = 0; i < length; i++) {
            result[i] = rdf.readByte();
        }
        return result;
    }
}
