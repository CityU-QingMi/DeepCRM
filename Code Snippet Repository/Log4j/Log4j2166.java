    @Test
    public void testCreateEventReturnsSameInstance() throws Exception {
        final ReusableLogEventFactory factory = new ReusableLogEventFactory();
        final LogEvent event1 = callCreateEvent(factory, "a", Level.DEBUG, new SimpleMessage("abc"), null);
        ReusableLogEventFactory.release(event1);
        final LogEvent event2 = callCreateEvent(factory, "b", Level.INFO, new SimpleMessage("xyz"), null);
        assertSame(event1, event2);

        ReusableLogEventFactory.release(event2);
        final LogEvent event3 = callCreateEvent(factory, "c", Level.INFO, new SimpleMessage("123"), null);
        assertSame(event2, event3);
        ReusableLogEventFactory.release(event3);
    }
