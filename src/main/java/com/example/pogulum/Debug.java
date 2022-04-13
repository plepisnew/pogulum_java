package com.example.pogulum;

import com.example.pogulum.util.Http;
import org.apache.tomcat.util.http.fileupload.FileUtils;

import javax.sound.midi.Sequence;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Debug {

    public static void downloadFile(URL url, String outputFileName) throws IOException
    {
        try (InputStream in = url.openStream();
             ReadableByteChannel rbc = Channels.newChannel(in);
             FileOutputStream fos = new FileOutputStream(outputFileName)) {
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        }
    }

    public static File combineFiles(List<File> files, File target) throws IOException {
        Vector<InputStream> inputStreams = new Vector<>();
        files.forEach(file -> {
            try {
                inputStreams.add(new FileInputStream(file));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        SequenceInputStream seq = new SequenceInputStream(inputStreams.elements());
        FileOutputStream fout = new FileOutputStream(target);
        int j;
        while((j = seq.read()) != -1){
            fout.write(j);
        }
        seq.close();
        fout.close();
        inputStreams.forEach(stream -> {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return target;
    }

    public static List<File> filesOf(List<String> urls){
        List<File> files = new ArrayList<>();
        urls.forEach(url -> {
            try {
                files.add(getFile(url));
            } catch (MalformedURLException | URISyntaxException e) {
                e.printStackTrace();
            }
        });
        return files;
    }

    private static File getFile(String url) throws MalformedURLException, URISyntaxException {
        return Paths.get(new URL(url).toURI()).toFile();
    }

    public static void main(String[] args) throws IOException {

        /*
         *  Q: How to obtain video source from clip url?
         *
         *  A: index.html asks for clip path, fetches source HTML and creates link to video source
         *
         *  String path = "https://clips.twitch.tv/PlumpYawningRabbitPogChamp-w4NH_wsOjd4hNbqz";
         *  String videoSource = getSource(path);
         */

//        String apiLink = "https://clips.twitch.tv/PlumpYawningRabbitPogChamp-w4NH_wsOjd4hNbqz";
//        downloadFile(new URL(apiLink), "api.mp4");
//
//        String path = "https://production.assets.clips.twitchcdn.net/AT-cm%7C1126229363.mp4?sig=36118856afc9103763ffc6311c039a62fd73cd47&token=%7B%22authorization%22%3A%7B%22forbidden%22%3Afalse%2C%22reason%22%3A%22%22%7D%2C%22clip_uri%22%3A%22https%3A%2F%2Fproduction.assets.clips.twitchcdn.net%2FAT-cm%257C1126229363.mp4%22%2C%22device_id%22%3A%22c30QngkNVIrkjJzBFImoYdi2um8E2Loj%22%2C%22expires%22%3A1649171183%2C%22user_id%22%3A%2281334040%22%2C%22version%22%3A2%7D";
//        downloadFile(new URL(path), "kek.mp4");

//        filesOf(List.of()) Bottom Text


        String url = "http://production.assets.clips.twitchcdn.net/AT-cm%7C1232560983.mp4?sig=21af7e6f432754c368cd7afbda828a2a03bebfce&token=%7B%22authorization%22%3A%7B%22forbidden%22%3Afalse%2C%22reason%22%3A%22%22%7D%2C%22clip_uri%22%3A%22https%3A%2F%2Fproduction.assets.clips.twitchcdn.net%2FAT-cm%257C1232560983.mp4%22%2C%22device_id%22%3A%22c30QngkNVIrkjJzBFImoYdi2um8E2Loj%22%2C%22expires%22%3A1649876220%2C%22user_id%22%3A%2281334040%22%2C%22version%22%3A2%7D";
        combineFiles(filesOf(List.of(url)), new File("test.mp4"));
    }
}
