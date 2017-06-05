package org.bearmug.aws.actions;

import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by pavel on 6/5/17.
 */
public class SetUserAction implements Action {
    private final long chatId;

    public SetUserAction(long chatId) {
        this.chatId = chatId;
    }

    @Override
    public BotApiMethod respond() {
        return new SendMessage(chatId, "Теперь вы посетитель, удачной экскурсии!")
                .setReplyMarkup(new InlineKeyboardMarkup().setKeyboard(Arrays.asList(
                        Collections.singletonList(
                                new InlineKeyboardButton("Что сейчас рядом со мной?").setCallbackData("/extras")
                        ),
                        Collections.singletonList(
                                new InlineKeyboardButton("Дополнительная информация").setCallbackData("/info")
                        ),
                        Collections.singletonList(
                                new InlineKeyboardButton("Я передумал, где метро?").setCallbackData("/metro")
                        ),
                        Collections.singletonList(
                                new InlineKeyboardButton("Я потерялся, отведите меня к гиду!").setCallbackData("/find_guide")
                        ),
                        Collections.singletonList(
                                new InlineKeyboardButton("Перестартовать").setCallbackData("/start")
                        )
                )));
    }
}
