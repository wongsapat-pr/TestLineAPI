package com.example.linebottest.controller;
import com.example.linebottest.LineBotTestApplication;
import com.google.common.io.ByteStreams;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.ExecutionException;


import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.client.MessageContentResponse;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.ImageMessageContent;
import com.linecorp.bot.model.message.ImageMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@LineMessageHandler
public class LinebotControllerImage {
    @Autowired
    private LineMessagingClient lineMessagingClient;
    
    @EventMapping
    public void handleImageMessage(MessageEvent<ImageMessageContent> event) {
        log.info(event.toString());
        ImageMessageContent content = event.getMessage();
        String replyToken = event.getReplyToken();
    
        try {
            MessageContentResponse response = lineMessagingClient.getMessageContent(
                content.getId()).get();
            DownloadedContent jpg = saveContent("jpg", response);
            DownloadedContent previewImage = createTempFile("jpg");
    
            system("convert", "-resize", "240x",
                    jpg.path.toString(),
                    previewImage.path.toString());
    
            reply(replyToken, new ImageMessage(jpg.getUri(), previewImage.getUri()));
    
        } catch (InterruptedException | ExecutionException e) {
            reply(replyToken, new TextMessage("Cannot get image: " + content));
            throw new RuntimeException(e);
        }
    
    }
    
    private void reply(String replyToken, TextMessage textMessage) {
        try {
            BotApiResponse response = lineMessagingClient.replyMessage(
                    new ReplyMessage(replyToken, textMessage)
            ).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private void reply(String replyToken, ImageMessage imageMessage) {
        try {
            BotApiResponse response = lineMessagingClient.replyMessage(
                    new ReplyMessage(replyToken, imageMessage)
            ).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private void system(String... args) {
        ProcessBuilder processBuilder = new ProcessBuilder(args);
        try {
            Process start = processBuilder.start();
            int i = start.waitFor();
            log.info("result: {} => {}", Arrays.toString(args), i);
        } catch (InterruptedException e) {
            log.info("Interrupted", e);
            Thread.currentThread().interrupt();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
    
    private static DownloadedContent saveContent(String ext, 
                                                 MessageContentResponse response) {
        log.info("Content-type: {}", response);
        DownloadedContent tempFile = createTempFile(ext);
        try (OutputStream outputStream = Files.newOutputStream(tempFile.path)) {
            ByteStreams.copy(response.getStream(), outputStream);
            log.info("Save {}: {}", ext, tempFile);
            return tempFile;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
    
    private static DownloadedContent createTempFile(String ext) {
        String fileName = LocalDateTime.now() + "-" 
                          + UUID.randomUUID().toString() 
                          + "." + ext;
        Path tempFile = LineBotTestApplication.downloadedContentDir.resolve(fileName);
        tempFile.toFile().deleteOnExit();
        return new DownloadedContent(tempFile, 
                                     createUri("/downloaded/" + tempFile.getFileName()));
    
    }
    
    private static String createUri(String path) {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(path).toUriString();
    }
    
    @Value
    public static class DownloadedContent {
        Path path;
        String uri;
    }
}
