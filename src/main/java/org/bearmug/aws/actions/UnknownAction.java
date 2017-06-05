package org.bearmug.aws.actions;

import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class UnknownAction implements Action {
    private final long chatId;

    private final List<String> responses = Arrays.asList(
            "Эта команда мне, как боту, непонятна...",
            "Unknown request, please restart conversation",
            "Попробуйте сначала, перейдите к стартовому диалогу",
            "Команда, которую вы ввели, не существует",
            "Что бы это могло значить? Начните сначала!",
            "Непонятно...."
    );

    UnknownAction(long chatId) {
        this.chatId = chatId;
    }

    @Override
    public BotApiMethod respond() {
        return new SendMessage(chatId, randomResponse())
                .setReplyMarkup(new InlineKeyboardMarkup().setKeyboard(Arrays.asList(
                        Collections.singletonList(
                                new InlineKeyboardButton("Я гид").setCallbackData("/guide")
                        ),
                        Collections.singletonList(
                                new InlineKeyboardButton("Where is the guided tour!? Im a tourist!").setCallbackData("/visitor")
                        ),
                        Collections.singletonList(
                                new InlineKeyboardButton("Начать сначала!").setCallbackData("/start")
                        )
                )));
    }

    private String randomResponse() {
        return responses.get(new Random(System.currentTimeMillis()).nextInt(1000) % responses.size());
    }
}
