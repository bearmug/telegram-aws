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
    G_SHARE_EXTRAS("extras"),
    G_EXTRAS_1("g_extras_1"),
    G_EXTRAS_2("g_extras_2"),
    G_EXTRAS_3("g_extras_3"),
    G_SHARE_CONTEXT("g_context"),
    G_CONTEXT_LOCATION("g_location"),
    G_CONTEXT_TOUR("g_tour"),
    G_SHARE_INFO("g_info"),
    G_INFO_MYSELF("g_myself"),
    G_INFO_ORG("g_org"),
    G_INFO_MORE_TOURS("g_more"),

    /* visitor-related commands */
    V_REQUEST_INFO("v_info"),
    V_INFO_TOUR("v_tour"),
    V_INFO_MORE("v_more"),
    V_INFO_GUIDE("v_guide"),
    V_INFO_EXTRAS("v_extras"),
    V_METRO("metro"),
    V_I_AM_LOST("lost");

    final String value;

    InputCommand(String value) {
        this.value = value;
    }
}
