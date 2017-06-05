package org.bearmug.aws.actions;

import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;

public interface Action {

    BotApiMethod respond();

    static Action forInput(Update update) {
        return new DefaultRouter(update);
    }

    class DefaultRouter implements Action {
        private final Update update;

        DefaultRouter(Update update) {
            this.update = update;
        }

        @Override
        public BotApiMethod respond() {
            return new SendMessage(update.getMessage().getChatId(),
                    greetingsFor(update.getMessage().getChat().getFirstName()));
        }

        private String greetingsFor(String firstName) {
            return "Приветики, " + firstName + "!! Этот бот работает нормально!";
        }
    }
}
