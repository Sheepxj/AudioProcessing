package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ConnectAudio {
    public static void main(String[] args) {
        List<String> files = new ArrayList<>(3);
        files.add("J:\\CloudMusic\\宝宝巴士 - 数鸭子.mp3");
        files.add("J:\\CloudMusic\\中央民族乐团-百鸟朝凤 (唢呐独奏).mp3");
        try {
            combine("J:\\CloudMusic\\123.mp3",files);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean combine(String outFile, List<String> inFiles) throws Exception
    {
        File[] files = new File[inFiles.size()];
        for (int i = 0; i < files.length; i++)
        {
            files[i] = new File(inFiles.get(i));
        }
        FileInputStream fis = null;
        FileOutputStream fos = new FileOutputStream(outFile, true); // 合并其实就是文件的续写,写成true
        for (int i = 0; i < files.length; i++)
        {
            fis = new FileInputStream(files[i]);
            int len = 0;
            for (byte[] buf = new byte[1024 * 1024]; (len = fis.read(buf)) != -1;)
            {
                fos.write(buf, 0, len);
            }
        }
        fis.close();
        fos.close();
        return true;
    }
}
