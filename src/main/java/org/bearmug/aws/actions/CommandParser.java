package org.bearmug.aws.actions;

import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandParser {

    private final Logger logger = Logger.getLogger(CommandParser.class);
    private final Pattern pattern = Pattern.compile("/([a-z]+).*");
    private final long chatId;

    public CommandParser(Long chatId) {
        this.chatId = chatId;
    }

    public Action getCommand(String text) {
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            try {
                switch (InputCommand.valueOf(matcher.group(1).toUpperCase())) {
                    case START:
                        return new StartAction(chatId);
                    case HELP:
                        return new HelpAction(chatId);
                    case GUIDE:
                        return new SetGuideAction(chatId);
                    case VISITOR:
                        return new SetUserAction(chatId);
                    default:
                        return new UnknownAction(chatId);
                }
            } catch (IllegalArgumentException e) {
                logger.warn("There are no specific command yet, responding with default to: " + text);
                return new UnknownAction(chatId);
            }

        } else {
            return new UnknownAction(chatId);
        }
    }
}
