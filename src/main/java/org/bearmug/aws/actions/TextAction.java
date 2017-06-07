package org.bearmug.aws.actions;

import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TextAction implements Action {

    private final Map<String, String> actionsMap;
    private final Message message;
    private final String response;

    public TextAction(Message message, Map<String, String> actionsMap, String response) {
        this.message = message;
        this.actionsMap = actionsMap;
        this.response = response;
    }

    @Override
    public BotApiMethod respond() {
        final List<List<InlineKeyboardButton>> actions = actionsMap
                .entrySet()
                .stream()
                .map(entry ->
                        Collections.singletonList(
                                new InlineKeyboardButton(entry.getValue()).setCallbackData(entry.getKey())
                        ))
                .collect(Collectors.toList());

        return new SendMessage(message.getChatId(), response)
                .setReplyMarkup(new InlineKeyboardMarkup().setKeyboard(actions));
    }
}
