package org.bearmug.aws.actions;

import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TextAction implements Action {

    static class Button {
        private final String command;
        private final String text;

        public Button(String command, String text) {
            this.command = command;
            this.text = text;
        }
    }

    private final List<Button> buttonList;
    private final long replyTo;
    private final String response;

    public TextAction(long replyTo, String response, String... actions) {
        this.replyTo = replyTo;
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

        return new SendMessage(replyTo, response)
                .setReplyMarkup(new InlineKeyboardMarkup().setKeyboard(actions));
    }
}
