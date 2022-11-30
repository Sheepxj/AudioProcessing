package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class MixVideo {
    public static void main(String[] args) {
        String[] Vedios = {"J:\\CloudMusic\\数鸭子改.wav", "J:\\CloudMusic\\还在流浪改.wav"};

        String targetFile = "J:\\CloudMusic\\345.wav";

        uniteWavFile(Vedios,targetFile);

    }
    /**
     *
     * @param partsPaths 要合成的音频路径数组
     * @param unitedFilePath 输入合并结果数组
     */
    public static void uniteWavFile(String[] partsPaths, String unitedFilePath) {

        byte byte1[] = getByte(partsPaths[0]);
        byte byte2[] = getByte(partsPaths[1]);

        byte[] out = new byte[byte1.length];
        for (int i = 0; i < byte1.length; i++)
            out[i] = (byte) ((byte1[i] + byte2[i]) >> 1);

        try {
            FileOutputStream fos = new FileOutputStream(new File(unitedFilePath));
            fos.write(out);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static byte[] getByte(String path){
        File f = new File(path);
        InputStream in;
        byte bytes[] = null;
        try {
            in = new FileInputStream(f);
            bytes = new byte[(int) f.length()];
            in.read(bytes);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bytes;
    }


}
