package com.example.linebottest.LineController;

import java.util.concurrent.ExecutionException;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.StickerMessageContent;
import com.linecorp.bot.model.message.StickerMessage;
import com.linecorp.bot.model.response.BotApiResponse;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@LineMessageHandler
public class LinebotControllerSticker {
    @Autowired
    private LineMessagingClient lineMessagingClient;

    @EventMapping
    public void handleStickerMessage(MessageEvent<StickerMessageContent> event) {
    log.info(event.toString());
    StickerMessageContent message = event.getMessage();
    replysticker(event.getReplyToken(), new StickerMessage(message.getPackageId(), message.getStickerId()
    ));
    }
    private void replysticker(@NonNull String replyToken,@NonNull StickerMessage stickerMessage) {
        try {
            BotApiResponse response = lineMessagingClient.replyMessage(
                    new ReplyMessage(replyToken, stickerMessage)
            ).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
