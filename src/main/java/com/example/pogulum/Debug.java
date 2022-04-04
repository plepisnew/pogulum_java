package com.example.pogulum;

import org.apache.tomcat.util.http.fileupload.FileUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Paths;

public class Debug {

    public static void downloadFile(URL url, String outputFileName) throws IOException
    {
        try (InputStream in = url.openStream();
             ReadableByteChannel rbc = Channels.newChannel(in);
             FileOutputStream fos = new FileOutputStream(outputFileName)) {
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        }
    }

    public static File combineFiles(File... files){
        return new File("");
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

    }
}
