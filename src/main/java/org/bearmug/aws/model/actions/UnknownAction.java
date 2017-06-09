package org.bearmug.aws.model.actions;

import org.bearmug.aws.model.Command;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

class UnknownAction extends TextAction {
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

    UnknownAction(Command command) {
        super(
                command.chatId,
                command.messageId,
                randomResponse() + " Попробуйте отдать команду боту с помощью кнопок в чате под этим " +
                        "сообщением или просто перестартуйте диалог командой /start . Команды не умещаются на экране? " +
                        "Попробуйте скрыть буквеную клавиатуру. Нужна помощь? Жмите /help",
                "/start ->  Перестартовать",
                "/help  ->  Что все это значит?"
        );
    }

    private static String randomResponse() {
        return RESPONSES.get(new Random(System.currentTimeMillis()).nextInt(1000) % RESPONSES.size());
    }
}
