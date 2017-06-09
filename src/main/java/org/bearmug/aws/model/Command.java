package org.bearmug.aws.model;

public class Command {

    public final Long chatId;
    public final int messageId;
    public final String text;

    public Command(Long chatId, int messageId, String text) {
        this.chatId = chatId;
        this.messageId = messageId;
        this.text = text;
    }
}
