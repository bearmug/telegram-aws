package org.bearmug.aws.model.actions;

import org.bearmug.aws.model.Command;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestCommandParser {

    private ActionParser parser;

    @Test
    public void startCommandParsedOk() {
        parser = new ActionParser(new Command(1L, -1, "/start"));
        assertEquals(TextAction.class, parser.getAction().getClass());

        parser = new ActionParser(new Command(1L, -1, "/start "));
        assertEquals(TextAction.class, parser.getAction().getClass());

        parser = new ActionParser(new Command(1L, -1, "/start ololo"));
        assertEquals(TextAction.class, parser.getAction().getClass());

        parser = new ActionParser(new Command(1L, -1, "/start текст"));
        assertEquals(TextAction.class, parser.getAction().getClass());
    }

    @Test
    public void commandWithUnderscoreParsedOK() {
        parser = new ActionParser(new Command(1L, -1, "/share_location"));
        assertEquals(TextAction.class, parser.getAction().getClass());
    }

    @Test
    public void unknownCommandReplacementOk() {
        parser = new ActionParser(new Command(1L, -1, "/unknown"));
        assertEquals(UnknownAction.class, parser.getAction().getClass());

        parser = new ActionParser(new Command(1L, -1, "/"));
        assertEquals(UnknownAction.class, parser.getAction().getClass());

        parser = new ActionParser(new Command(1L, -1, "some text here"));
        assertEquals(UnknownAction.class, parser.getAction().getClass());

        parser = new ActionParser(new Command(1L, -1, "любой текст"));
        assertEquals(UnknownAction.class, parser.getAction().getClass());
    }
}
