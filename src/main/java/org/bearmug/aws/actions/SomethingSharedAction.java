package org.bearmug.aws.actions;

import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Collections;

public class SomethingSharedAction implements Action {
    private final long chatId;

    SomethingSharedAction(long chatId) {
        this.chatId = chatId;
    }

    @Override
    public BotApiMethod respond() {
        return new SendMessage(chatId, "Необходимая информация отправлена участникам экскурсии")
                .setReplyMarkup(new InlineKeyboardMarkup().setKeyboard(Collections.singletonList(
                        Collections.singletonList(
                                new InlineKeyboardButton("Вернуться").setCallbackData("/guide")
                        )
                )));
    }
}
