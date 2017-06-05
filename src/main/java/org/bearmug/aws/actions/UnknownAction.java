package org.bearmug.aws.actions;

import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;

public class UnknownAction implements Action {
    @Override
    public BotApiMethod respond() {
        return new SendMessage("", "Я не знаю что это за команда такая....");
    }
}
