    @Test
    public void testCreateEventOverwritesFields() throws Exception {
        final ReusableLogEventFactory factory = new ReusableLogEventFactory();
        final LogEvent event1 = callCreateEvent(factory, "a", Level.DEBUG, new SimpleMessage("abc"), null);
        assertEquals("logger", "a", event1.getLoggerName());
        assertEquals("level", Level.DEBUG, event1.getLevel());
        assertEquals("msg", new SimpleMessage("abc"), event1.getMessage());

        ReusableLogEventFactory.release(event1);
        final LogEvent event2 = callCreateEvent(factory, "b", Level.INFO, new SimpleMessage("xyz"), null);
        assertSame(event1, event2);

        assertEquals("logger", "b", event1.getLoggerName());
        assertEquals("level", Level.INFO, event1.getLevel());
        assertEquals("msg", new SimpleMessage("xyz"), event1.getMessage());
        assertEquals("logger", "b", event2.getLoggerName());
        assertEquals("level", Level.INFO, event2.getLevel());
        assertEquals("msg", new SimpleMessage("xyz"), event2.getMessage());
    }
