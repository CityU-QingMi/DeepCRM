    @SuppressWarnings("")
    private void verifyNanoTimeWithAllConstructors(final long expected) {
        assertEquals(expected, Log4jLogEvent.getNanoClock().nanoTime());

        assertEquals("No-arg constructor", expected, new Log4jLogEvent().getNanoTime());
        assertEquals("1-arg constructor", expected, new Log4jLogEvent(98).getNanoTime());
        assertEquals("6-arg constructor", expected, new Log4jLogEvent("l", null, "a", null, null, null).getNanoTime());
        assertEquals("7-arg constructor", expected, new Log4jLogEvent("l", null, "a", null, null, null, null)
                .getNanoTime());
        assertEquals("11-arg constructor", expected, new Log4jLogEvent("l", null, "a", null, null, null, null, null,
                null, null, 0).getNanoTime());
        assertEquals("12-arg factory method", expected, Log4jLogEvent.createEvent("l", null, "a", null, null, null,
                null, null, null, null, null, 0).getNanoTime());
    }
