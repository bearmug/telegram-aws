package org.bearmug.aws.model;

import org.bearmug.aws.model.actions.TextAction;
import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Response {

    public static class Button {
        private final String command;
        private final String text;

        public Button(String command, String text) {
            this.command = command;
            this.text = text;
        }
    }

    private final long chatId;
    private final int messageId;
    private final String responseText;
    private final List<Button> buttonList;

    public Response(long chatId, int messageId,
                    String responseText, String... availableActiona) {
        this.chatId = chatId;
        this.messageId = messageId;
        this.responseText = responseText;
        this.buttonList = buttons(availableActiona);
    }

    private List<Button> buttons(String... data) {
        return Arrays.stream(data)
                .map(entry -> entry.split("->"))
                .filter(entry -> entry.length == 2)
                .map(entry -> new Button(entry[0], entry[1]))
                .collect(Collectors.toList());
    }

    public BotApiMethod getResponse() {
        final List<List<InlineKeyboardButton>> actions = buttonList
                .stream()
                .map(entry ->
                        Collections.singletonList(
                                new InlineKeyboardButton(entry.text).setCallbackData(entry.command)
                        ))
                .collect(Collectors.toList());

        if (messageId > 0) {
            return new EditMessageText()
                    .setText(responseText)
                    .setChatId(chatId)
                    .setMessageId(messageId)
                    .setReplyMarkup(new InlineKeyboardMarkup().setKeyboard(actions));
        } else {
            return new SendMessage()
                    .setText(responseText)
                    .setChatId(chatId)
                    .setReplyMarkup(new InlineKeyboardMarkup().setKeyboard(actions));
        }
    }
}
