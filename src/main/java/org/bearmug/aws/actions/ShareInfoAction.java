package org.bearmug.aws.actions;

import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Arrays;
import java.util.Collections;

public class ShareInfoAction implements Action {
    private final long chatId;

    ShareInfoAction(long chatId) {
        this.chatId = chatId;
    }

    @Override
    public BotApiMethod respond() {
        return new SendMessage(chatId, "Поделитесь информацией о вас, экскурсионном бюро, ближайших экскурсиях")
                .setReplyMarkup(new InlineKeyboardMarkup().setKeyboard(Arrays.asList(
                        Collections.singletonList(
                                new InlineKeyboardButton("Информация обо мне, гиде").setCallbackData("/post")
                        ),
                        Collections.singletonList(
                                new InlineKeyboardButton("Информация об экскурсионном бюро").setCallbackData("/post")
                        ),
                        Collections.singletonList(
                                new InlineKeyboardButton("Ближайшие экскурсии").setCallbackData("/post")
                        ),
                        Collections.singletonList(
                                new InlineKeyboardButton("Запросить обратную связь").setCallbackData("/post")
                        ),
                        Collections.singletonList(
                                new InlineKeyboardButton("Вернуться").setCallbackData("/guide")
                        )
                )));
    }
}
