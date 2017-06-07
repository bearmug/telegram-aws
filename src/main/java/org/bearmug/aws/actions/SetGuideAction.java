package org.bearmug.aws.actions;

import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Arrays;
import java.util.Collections;

public class SetGuideAction implements Action {
    private final long chatId;

    SetGuideAction(long chatId) {
        this.chatId = chatId;
    }

    @Override
    public BotApiMethod respond() {
        return new EditMessageText().setChatId(chatId).setText("Теперь вы гид, экскурсия запущена!")
                .setReplyMarkup(new InlineKeyboardMarkup().setKeyboard(Arrays.asList(
                        Collections.singletonList(
                                new InlineKeyboardButton("Сопроводительный материал").setCallbackData("/extras")
                        ),
                        Collections.singletonList(
                                new InlineKeyboardButton("Информация").setCallbackData("/info")
                        ),
                        Collections.singletonList(
                                new InlineKeyboardButton("Поискать потерявшихся посетителей").setCallbackData("/share_location")
                        ),
                        Collections.singletonList(
                                new InlineKeyboardButton("Перестартовать").setCallbackData("/start")
                        )
                )));
    }
}
