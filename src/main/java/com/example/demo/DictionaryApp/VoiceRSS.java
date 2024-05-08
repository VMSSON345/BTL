
package com.example.demo.DictionaryApp;

import com.voicerss.tts.AudioFormat;
import com.voicerss.tts.Languages;
import com.voicerss.tts.VoiceParameters;
import com.voicerss.tts.VoiceProvider;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class VoiceRSS {
    private static final String API_KEY = "1386ea5b542b43a2ac1cedd867b7af3c";
    private static final String AUDIO_PATH = "D:\\demo\\demo\\src\\main\\resources\\media\\audio.war";

    public static String voiceNameUS;
    public static String voiceNameUK;
    public static String language = "en-gb";
    public static String Name = "Linda";
    public static double speed = 1;


    public static void speakWord(String word) throws Exception {
        VoiceProvider tts = new VoiceProvider(API_KEY);
        VoiceParameters params = new VoiceParameters(word, AudioFormat.Format_44KHZ.AF_44khz_16bit_stereo);
        params.setBase64(false);
        params.setLanguage(language);
        params.setVoice(Name);
        params.setRate((int) Math.round(-2.9936 * speed * speed + 15.2942 * speed - 12.7612));
        byte[] voice = tts.speech(params);

        FileOutputStream fos = new FileOutputStream(AUDIO_PATH);
        fos.write(voice);
        fos.flush();
        fos.close();
    }

    public static void playAudio(String audioFilePath) {
        try {
            File audioFile = new File(audioFilePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            // Lấy ra Clip từ AudioSystem
            Clip clip = AudioSystem.getClip();
            // Mở Clip và load dữ liệu từ AudioInputStream
            clip.open(audioStream);
            // Bắt đầu phát lại âm thanh
            clip.start();
            // Đợi cho đến khi âm thanh phát lại hoàn thành
            while (!clip.isRunning()) {
                Thread.sleep(2);
            }
            while (clip.isRunning()) {
                Thread.sleep(2);
            }
            // Đóng luồng và giải phóng tài nguyên
            clip.close();
            audioStream.close();
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            // Thực hiện gọi hàm speakWord để tạo và lưu file âm thanh
            speakWord("Hello, how are you?");

            // Sau đó, sử dụng hàm playAudio để phát lại file âm thanh đã tạo
            playAudio(VoiceRSS.AUDIO_PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}



