package org.bearmug.aws.model;

import org.bearmug.aws.model.actions.ActionParser;
import org.bearmug.aws.model.actions.TextAction;
import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.objects.CallbackQuery;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public interface Action {

    BotApiMethod respond();

    static Action forInput(Update update) {
        Message message = update.getMessage();
        CallbackQuery callbackQuery = update.getCallbackQuery();
        final Command command;
        if (message != null) {
            command = new Command(message.getChatId(), -1, message.getText());
        } else if (callbackQuery != null) {
            command = new Command(
                    callbackQuery.getMessage().getChatId(),
                    callbackQuery.getMessage().getMessageId(),
                    callbackQuery.getData());
        } else {
            throw new IllegalStateException("Wrong input data: " + update);
        }

        return new ActionParser(command).getAction();
    }

    default List<TextAction.Button> buttons(String... data) {
        return Arrays.stream(data)
                .map(entry -> entry.split("->"))
                .filter(entry -> entry.length == 2)
                .map(entry -> new TextAction.Button(entry[0], entry[1]))
                .collect(Collectors.toList());
    }
}

