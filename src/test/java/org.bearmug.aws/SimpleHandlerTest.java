package org.bearmug.aws;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class SimpleHandlerTest {

    private final RequestHandler<String, String> handler = new SimpleHandler();

    @Test
    public void testNullInput() {
        assertEquals(
                "<response in CAPS: ><input is empty>",
                handler.handleRequest(null, Mockito.mock(Context.class)));
    }

    @Test
    public void testValidInput() {
        assertEquals(
                "<response in CAPS: >VALID INPUT",
                handler.handleRequest("valid input", Mockito.mock(Context.class)));
    }

    @Test
    public void testCapsInput() {
        assertEquals(
                "<response in CAPS: >CAPS",
                handler.handleRequest("CAPS", Mockito.mock(Context.class)));
    }
}
