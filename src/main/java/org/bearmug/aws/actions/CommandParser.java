package org.bearmug.aws.actions;

import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandParser {

    private final Logger logger = Logger.getLogger(CommandParser.class);
    private final Pattern pattern = Pattern.compile("/([a-z]+).*");

    public Action getCommand(String text) {
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            switch (InputCommand.valueOf(matcher.group(1).toUpperCase())) {
                case START:
                    return new StartAction();
                default:
                    return new UnknownAction();
            }

        } else {
            return new UnknownAction();
        }
    }
}
