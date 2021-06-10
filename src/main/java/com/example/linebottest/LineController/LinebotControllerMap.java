package com.example.linebottest.LineController;

import java.util.concurrent.ExecutionException;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.LocationMessageContent;
import com.linecorp.bot.model.message.LocationMessage;
import com.linecorp.bot.model.response.BotApiResponse;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@LineMessageHandler
public class LinebotControllerMap {
    @Autowired
    private LineMessagingClient lineMessagingClient;
    
    @EventMapping
    public void handleLocationMessage(MessageEvent<LocationMessageContent> event) {
    log.info(event.toString());
    LocationMessageContent message = event.getMessage();
    reply(event.getReplyToken(), new LocationMessage(
            (message.getTitle() == null) ? "Location replied" : message.getTitle(),
            message.getAddress(),
            message.getLatitude(),
            message.getLongitude()
    ));
}

    private void reply(String replyToken, LocationMessage locationMessage) {
        try {
            BotApiResponse response = lineMessagingClient.replyMessage(
                    new ReplyMessage(replyToken, locationMessage)
            ).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
