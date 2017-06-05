package org.bearmug.aws.actions;

import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;

public interface Action {

    BotApiMethod respond();

    static Action forInput(Update update) {
        if (update.getMessage() != null) {
            return new CommandParser(update.getMessage().getChatId())
                    .getCommand(update.getMessage().getText());
        } else if (update.getCallbackQuery() != null) {
            return new CommandParser(update.getCallbackQuery().getMessage().getChatId())
                    .getCommand(update.getCallbackQuery().getData());
        } else {
            throw new IllegalStateException("Wrong input data: " + update);
        }
    }
}

enum InputCommand {

    /* basic commands */
    START("Start tour"),
    HELP("Get some help"),
    UNKNOWN("unknown"),

    /* behavior type commands */
    GUIDE("guide"),
    VISITOR("visitor"),

    /* guide-related commands */
    G_INVITE("g_invite"), // not implemented
    EXTRAS("guided tour extras"),
    G_EXTRAS_1("g_extras_1"),
    G_EXTRAS_2("g_extras_2"),
    G_EXTRAS_3("g_extras_3"),
    G_SHARE_CONTEXT("g_context"),
    SHARE_LOCATION("update tourists with guide location"),
    G_CONTEXT_TOUR("g_tour"),
    INFO("share related information"),
    G_INFO_MYSELF("g_myself"),
    G_INFO_ORG("g_org"),
    G_INFO_MORE_TOURS("g_more"),
    POST("dummy update"),

    /* visitor-related commands */
    V_REQUEST_INFO("v_info"),
    V_INFO_TOUR("v_tour"),
    METRO("metro location and route request"),
    FIND_GUIDE("locate guide and navigate there"),
    V_INFO_EXTRAS("v_extras"),
    V_METRO("metro"),
    V_I_AM_LOST("lost");

    final String value;

    InputCommand(String value) {
        this.value = value;
    }
}
