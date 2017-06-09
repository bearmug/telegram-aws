package org.bearmug.aws.model.actions;

import org.bearmug.aws.model.Action;
import org.bearmug.aws.model.Response;
import org.telegram.telegrambots.api.methods.BotApiMethod;

public class TextAction implements Action {

    private final Response response;

    TextAction(Response response) {
        this.response = response;
    }

    TextAction(Long chatId, int messageId, String responseText, String... availableMessages) {
        this(new Response(chatId, messageId, responseText, availableMessages));
    }

    @Override
    public BotApiMethod respond() {
        return response.getResponse();
    }
}
