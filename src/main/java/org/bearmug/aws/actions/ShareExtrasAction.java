package org.bearmug.aws.actions;

import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Arrays;
import java.util.Collections;

public class ShareExtrasAction implements Action {
    private final long chatId;

    ShareExtrasAction(long chatId) {
        this.chatId = chatId;
    }

    @Override
    public BotApiMethod respond() {
        return new SendMessage(chatId, "Поделитесь дополнительными материалами с участниками экскурсии")
                .setReplyMarkup(new InlineKeyboardMarkup().setKeyboard(Arrays.asList(
                        Collections.singletonList(
                                new InlineKeyboardButton("Тщательно подобранные ссылочки").setCallbackData("/post")
                        ),
                        Collections.singletonList(
                                new InlineKeyboardButton("Уникальные фото").setCallbackData("/post")
                        ),
                        Collections.singletonList(
                                new InlineKeyboardButton("Негуглимые истории").setCallbackData("/post")
                        ),
                        Collections.singletonList(
                                new InlineKeyboardButton(
                                        "Цитата из первоисточника, вызвавшая споры среди посетителей экскурсии")
                                        .setCallbackData("/post")
                        ),
                        Collections.singletonList(
                                new InlineKeyboardButton("Вернуться").setCallbackData("/guide")
                        )
                )));
    }
}
