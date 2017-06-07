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
        parser = new CommandParser(1L, "/start");
        assertEquals(TextAction.class, parser.getCommand().getClass());

        parser = new CommandParser(1L, "/start ");
        assertEquals(TextAction.class, parser.getCommand().getClass());

        parser = new CommandParser(1L, "/start ololo");
        assertEquals(TextAction.class, parser.getCommand().getClass());

        parser = new CommandParser(1L, "/start текст");
        assertEquals(TextAction.class, parser.getCommand().getClass());
    }

    @Test
    public void commandWithUnderscoreParsedOK() {
        parser = new CommandParser(1L, "/share_location");
        assertEquals(TextAction.class, parser.getCommand().getClass());
    }

    @Test
    public void unknownCommandReplacementOk() {
        parser = new CommandParser(1L, "/unknown");
        assertEquals(UnknownAction.class, parser.getCommand().getClass());

        parser = new CommandParser(1L, "/");
        assertEquals(UnknownAction.class, parser.getCommand().getClass());

        parser = new CommandParser(1L, "some text here");
        assertEquals(UnknownAction.class, parser.getCommand().getClass());

        parser = new CommandParser(1L, "любой текст");
        assertEquals(UnknownAction.class, parser.getCommand().getClass());
    }
}
