package org.bearmug.aws.model.actions;

import org.bearmug.aws.model.Action;
import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TextAction implements Action {

    public static class Button {
        private final String command;
        private final String text;

        public Button(String command, String text) {
            this.command = command;
            this.text = text;
        }
    }

    private final List<Button> buttonList;
    private final long chatId;
    private final int messageId;
    private final String response;

    TextAction(long chatId, int messageId, String response, String... actions) {
        this.chatId = chatId;
        this.messageId = messageId;
        this.response = response;
        this.buttonList = buttons(actions);
    }

    @Override
    public BotApiMethod respond() {
        final List<List<InlineKeyboardButton>> actions = buttonList
                .stream()
                .map(entry ->
                        Collections.singletonList(
                                new InlineKeyboardButton(entry.text).setCallbackData(entry.command)
                        ))
                .collect(Collectors.toList());

        if (messageId > 0) {
            return new EditMessageText()
                    .setText(response)
                    .setChatId(chatId)
                    .setMessageId(messageId)
                    .setReplyMarkup(new InlineKeyboardMarkup().setKeyboard(actions));
        } else {
            return new SendMessage()
                    .setText(response)
                    .setChatId(chatId)
                    .setReplyMarkup(new InlineKeyboardMarkup().setKeyboard(actions));
        }

    }
}
