package org.bearmug.aws.actions;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestCommandParser {

    private final CommandParser parser = new CommandParser();

    @Test
    public void startCommandParsedOk() {
        assertEquals(StartAction.class, parser.getCommand("/start").getClass());
        assertEquals(StartAction.class, parser.getCommand("/start ").getClass());
        assertEquals(StartAction.class, parser.getCommand("/start and do something").getClass());
        assertEquals(StartAction.class, parser.getCommand("/start здесь лишний текст").getClass());
    }

    @Test
    public void unknownCommandReplacementOk() {
        assertEquals(UnknownAction.class, parser.getCommand("/unknown").getClass());
        assertEquals(UnknownAction.class, parser.getCommand("/").getClass());
        assertEquals(UnknownAction.class, parser.getCommand("some text here").getClass());
        assertEquals(UnknownAction.class, parser.getCommand("любой текст").getClass());
    }
}
