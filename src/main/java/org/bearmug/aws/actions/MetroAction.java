package org.bearmug.aws.actions;

import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Arrays;
import java.util.Collections;

public class MetroAction implements Action {
    private final long chatId;

    MetroAction(long chatId) {
        this.chatId = chatId;
    }

    @Override
    public BotApiMethod respond() {
        return new SendMessage(chatId, "Тут будет прведен маршрут до метро! Спасибо за посещение! Вот тут будут очень полезные ссылочки!")
                .setReplyMarkup(new InlineKeyboardMarkup().setKeyboard(Arrays.asList(
                        Collections.singletonList(
                                new InlineKeyboardButton("Нет! Отведите меня к гиду").setCallbackData("/find_guide")
                        ),
                        Collections.singletonList(
                                new InlineKeyboardButton("Перестартовать").setCallbackData("/start")
                        )
                )));
    }
}
