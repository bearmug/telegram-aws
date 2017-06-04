package org.bearmug.aws;

import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;

public interface Router {

    BotApiMethod answer();

    static Router route(Update update) {
        return new DefaultRouter(update);
    }

    class DefaultRouter implements Router {
        private final Update update;

        DefaultRouter(Update update) {
            this.update = update;
        }

        @Override
        public BotApiMethod answer() {
            return new SendMessage(update.getMessage().getChatId(),
                    greetingsFor(update.getMessage().getChat().getFirstName()));
        }

        private String greetingsFor(String firstName) {
            return "Приветики, " + firstName + "!! Этот бот работает нормально!";
        }
    }
}
