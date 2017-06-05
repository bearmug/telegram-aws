package org.bearmug.aws.actions;

import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Arrays;
import java.util.Collections;

public class FindGuideAction implements Action {
    private final long chatId;

    FindGuideAction(long chatId) {
        this.chatId = chatId;
    }

    @Override
    public BotApiMethod respond() {
        return new SendMessage(chatId, "Вот ваш маршрут до гида. Тут совсем недалеко!")
                .setReplyMarkup(new InlineKeyboardMarkup().setKeyboard(Arrays.asList(
                        Collections.singletonList(
                                new InlineKeyboardButton("Я передумал, где метро?").setCallbackData("/metro")
                        ),
                        Collections.singletonList(
                                new InlineKeyboardButton("Перестартовать").setCallbackData("/start")
                        )
                )));
    }
}
