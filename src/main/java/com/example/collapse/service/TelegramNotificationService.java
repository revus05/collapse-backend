package com.example.collapse.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Service
public class TelegramNotificationService {

    private final TelegramClient telegramClient;

    @Value("${telegram.bot.chat-id}")
    private String chatId;

    public TelegramNotificationService(TelegramClient telegramClient) {
        this.telegramClient = telegramClient;
    }

    public void sendOrderNotification(String orderInfo) {
        try {
            SendMessage message = SendMessage.builder()
                    .chatId(chatId)
                    .text(orderInfo)
                    .parseMode("HTML")
                    .build();

            telegramClient.execute(message);
        } catch (TelegramApiException e) {
            System.out.println("Не удалось отправить уведомление в Telegram");
        }
    }
}