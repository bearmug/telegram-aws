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

enum Command {
    /* standard commands */
    START,
    HELP,

    /* behavior type commands */
    I_AM_THE_GUIDE,
    I_AM_THE_VISITOR,

    /* guide-related commands */
    G_INVITE_VISITORS,
    G_SHARE_EXTRAS,
    G_EXTRAS_1,
    G_EXTRAS_2,
    G_EXTRAS_3,
    G_SHARE_CONTEXT,
    G_CONTEXT_LOCATION,
    G_CONTEXT_TOUR,
    G_SHARE_INFO,
    G_INFO_MYSELF,
    G_INFO_ORG,
    G_INFO_MORE_TOURS,

    /* visitor-related commands */
    V_REQUEST_INFO,
    V_INFO_TOUR,
    V_INFO_MORE,
    V_INFO_GUIDE,
    V_INFO_EXTRAS,
    V_I_AM_LOST
}
