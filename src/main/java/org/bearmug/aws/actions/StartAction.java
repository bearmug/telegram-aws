package org.bearmug.aws.actions;

import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;

public class StartAction implements Action {
    @Override
    public BotApiMethod respond() {
        return new SendMessage("", "Старт!!!");
    }
}
