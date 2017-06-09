package org.bearmug.aws.model;

/**
 * Input command to do something.
 */
public class Command {

    public final Long chatId;
    public final int messageId;
    public final String command;

    public Command(Long chatId, int messageId, String command) {
        this.chatId = chatId;
        this.messageId = messageId;
        this.command = command;
    }
}
