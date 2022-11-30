package util;

import it.sauronsoftware.jave.*;

import java.io.File;


public class AudioForm {
    public static void main(String[] args) throws Exception {
        Mp3Test("J:\\CloudMusic\\宝宝巴士 - 数鸭子.wav","J:\\CloudMusic\\数鸭子改.wav");

        // //获取音频文件的基本信息
        // String fileName ="J:\\CloudMusic\\还在流浪改.wav";
        // File wav = new File( fileName );
        // Encoder encoder = new Encoder();
        // try {
        //     MultimediaInfo mul = encoder.getInfo(wav);
        //     int bit = mul.getAudio().getBitRate();
        //     int rate = mul.getAudio().getSamplingRate();
        //     int chan = mul.getAudio().getChannels();
        //     System.out.println(bit);
        //     System.out.println(rate);
        //     System.out.println(chan);
        // } catch (EncoderException e) {
        //     e.printStackTrace();
        // }
    }
    public static void Mp3Test(String inputPath, String outputPath){
        AudioAttributes audio = new AudioAttributes();
        audio.setCodec("libmp3lame");
        audio.setBitRate(128000);//设置比特率
        audio.setSamplingRate(8000);//设置采样率
        System.out.println(audio);
        audio.setChannels(1);//设置音频通道数

        //录入音频信息
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setFormat("wav");//设置文件格式
        attrs.setAudioAttributes(audio);

        //编码
        Encoder encoder = new Encoder();
        File inputFile = new File(inputPath);
        File outputFile = new File(outputPath);
        try {
            encoder.encode(inputFile, outputFile, attrs);
            WavToPcm.convertAudioFiles(outputPath);
        }catch (IllegalArgumentException | EncoderException e) {
            e.printStackTrace();
        }
    }


}
