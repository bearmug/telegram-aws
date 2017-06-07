package org.bearmug.aws.actions;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.api.objects.Message;

import static org.junit.Assert.assertEquals;

public class TestCommandParser {

    private CommandParser parser;

    @Test
    public void startCommandParsedOk() {
        parser = new CommandParser(1L, -1, "/start");
        assertEquals(TextAction.class, parser.getCommand().getClass());

        parser = new CommandParser(1L, -1, "/start ");
        assertEquals(TextAction.class, parser.getCommand().getClass());

        parser = new CommandParser(1L, -1, "/start ololo");
        assertEquals(TextAction.class, parser.getCommand().getClass());

        parser = new CommandParser(1L, -1, "/start текст");
        assertEquals(TextAction.class, parser.getCommand().getClass());
    }

    @Test
    public void commandWithUnderscoreParsedOK() {
        parser = new CommandParser(1L, -1, "/share_location");
        assertEquals(TextAction.class, parser.getCommand().getClass());
    }

    @Test
    public void unknownCommandReplacementOk() {
        parser = new CommandParser(1L, -1, "/unknown");
        assertEquals(UnknownAction.class, parser.getCommand().getClass());

        parser = new CommandParser(1L, -1, "/");
        assertEquals(UnknownAction.class, parser.getCommand().getClass());

        parser = new CommandParser(1L, -1, "some text here");
        assertEquals(UnknownAction.class, parser.getCommand().getClass());

        parser = new CommandParser(1L, -1, "любой текст");
        assertEquals(UnknownAction.class, parser.getCommand().getClass());
    }
}
