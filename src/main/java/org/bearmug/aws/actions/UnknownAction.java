package org.bearmug.aws.actions;

import org.telegram.telegrambots.api.objects.Message;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class UnknownAction extends TextAction {
    private static final List<String> RESPONSES = Arrays.asList(
            "Эта команда мне, как боту, непонятна...",
            "Unknown request, please restart conversation",
            "Попробуйте сначала, перейдите к стартовому диалогу",
            "Команда, которую вы ввели, не существует",
            "Что бы это могло значить? Начните сначала!",
            "Этот функционал временно недоступен. Извинити!",
            "Зато есть куда совершенствовать систему!",
            "Этого не могло произойти!",
            "Непонятно...."
    );

    UnknownAction(Message message) {
        super(
                message,
                randomResponse(),
                "/start ->  Перестартовать",
                "/help  ->  Что все это значит?"
        );
    }

    private static String randomResponse() {
        return RESPONSES.get(new Random(System.currentTimeMillis()).nextInt(1000) % RESPONSES.size());
    }
}
