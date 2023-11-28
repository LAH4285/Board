package com.example.board.core.utils;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SignUpMessageSender {
    public static final String API_KEY = "NCSNDZSYANOM9MS5";

    public static final String APISECRETKEY = "5W27WQDPA01GJUHOIK3XZCV10TSWATAM";

    private static final String DOMAIN = "https://api.solapi.com";


    private static DefaultMessageService messageService = null;

    @Bean
    public void initialize() {
        messageService = NurigoApp.INSTANCE.initialize(API_KEY, APISECRETKEY, DOMAIN);
    }

    public static void sendMessage(String fromPhoneNumber, String toPhoneNumber, String text) {
        Message message = new Message();

        message.setFrom(fromPhoneNumber);
        message.setTo(toPhoneNumber);
        message.setText(text);

        SingleMessageSentResponse response = messageService.sendOne(new SingleMessageSendingRequest(message));
        System.out.println(response);
    }
}
