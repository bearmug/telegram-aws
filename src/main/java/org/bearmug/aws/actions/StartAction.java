package org.bearmug.aws.actions;

import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Arrays;
import java.util.Collections;

public class StartAction implements Action {
    private final long chatId;

    StartAction(long chatId) {
        this.chatId = chatId;
    }

    @Override
    public BotApiMethod respond() {
        return new SendMessage(chatId, "Приступим! Кто ты, гид или посетитель???")
                .setReplyMarkup(new InlineKeyboardMarkup().setKeyboard(Arrays.asList(
                        Collections.singletonList(
                                new InlineKeyboardButton("Я гид!!!").setCallbackData("/guide")
                        ),
                        Collections.singletonList(
                                new InlineKeyboardButton("Посетитель я, пришел экскурсию посмотреть").setCallbackData("/visitor")
                        ),
                        Collections.singletonList(
                                new InlineKeyboardButton("Что все это значит?").setCallbackData("/help")
                        )
                )));
    }
}
