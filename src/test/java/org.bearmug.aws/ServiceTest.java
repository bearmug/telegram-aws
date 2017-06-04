package org.bearmug.aws;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ServiceTest {

    @Test
    public void testClaim() {
        assertEquals("This is proclamation!", new Service().claim());
    }
}
