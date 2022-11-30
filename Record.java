import javax.sound.sampled.TargetDataLine;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


public class Record implements Runnable{
    TargetDataLine td = null;

    Boolean stopflag = false;

    ByteArrayOutputStream baos = null;
    //定义存放录音的字节数组,作为缓冲区
    byte bts[] = new byte[10000];
    //将字节数组包装到流里，最终存入到baos中
    //重写run函数
    @Override
    public void run() {
        baos = new ByteArrayOutputStream();
        try {
            System.out.println("ok3");
            stopflag = false;
            while (stopflag != true) {
                //当停止录音没按下时，该线程一直执行
                //从数据行的输入缓冲区读取音频数据。
                //要读取bts.length长度的字节,cnt 是实际读取的字节数
                int cnt = td.read(bts, 0, bts.length);
                if (cnt > 0) {
                    baos.write(bts, 0, cnt);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭打开的字节数组流
                if (baos != null) {
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                td.drain();
                td.close();
            }
        }
    }
}
