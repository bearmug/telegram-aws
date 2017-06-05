package org.bearmug.aws.actions;

import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Arrays;
import java.util.Collections;

public class HelpAction implements Action {
    private long chatId;

    public HelpAction(long chatId) {
        this.chatId = chatId;
    }


    @Override
    public BotApiMethod respond() {
        return new SendMessage(chatId, "Это справочка по приложению. Здесь предложены подсказки по различным вопросам " +
                "и доступным командам.")
                .setReplyMarkup(new InlineKeyboardMarkup().setKeyboard(Arrays.asList(
                        Collections.singletonList(
                                new InlineKeyboardButton("Понятно, я посетитель.").setCallbackData("/visitor")
                        ),
                        Collections.singletonList(
                                new InlineKeyboardButton("Нет, я гид!!!!").setCallbackData("/guide")
                        ),
                        Collections.singletonList(
                                new InlineKeyboardButton("Я передумал! где здесь метро?").setCallbackData("/metro")
                        ),
                        Collections.singletonList(
                                new InlineKeyboardButton("Пожалуйста, в начало!").setCallbackData("/start")
                        )
                )));
    }
}
