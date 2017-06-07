package org.bearmug.aws.actions;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.api.objects.Message;

import static org.junit.Assert.assertEquals;

public class TestCommandParser {

    private CommandParser parser;
    private Message message;

    @Before
    public void setUp() {
        message = Mockito.mock(Message.class);
        parser = new CommandParser(message);
    }

    @Test
    public void startCommandParsedOk() {
        Mockito.when(message.getText()).thenReturn("/start");
        assertEquals(TextAction.class, parser.getCommand().getClass());

        Mockito.when(message.getText()).thenReturn("/start ");
        assertEquals(TextAction.class, parser.getCommand().getClass());

        Mockito.when(message.getText()).thenReturn("/start ololo");
        assertEquals(TextAction.class, parser.getCommand().getClass());

        Mockito.when(message.getText()).thenReturn("/start текст");
        assertEquals(TextAction.class, parser.getCommand().getClass());
    }

    @Test
    public void unknownCommandReplacementOk() {
        Mockito.when(message.getText()).thenReturn("/unknown");
        assertEquals(UnknownAction.class, parser.getCommand().getClass());

        Mockito.when(message.getText()).thenReturn("/");
        assertEquals(UnknownAction.class, parser.getCommand().getClass());

        Mockito.when(message.getText()).thenReturn("some text here");
        assertEquals(UnknownAction.class, parser.getCommand().getClass());

        Mockito.when(message.getText()).thenReturn("любой текст");
        assertEquals(UnknownAction.class, parser.getCommand().getClass());
    }
}
