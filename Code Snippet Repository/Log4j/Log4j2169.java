    @SuppressWarnings("")
    @Test
    public void testCreateEventInitFieldsProperly() throws Exception {
        final ReusableLogEventFactory factory = new ReusableLogEventFactory();
        final LogEvent event = callCreateEvent(factory, "logger", Level.INFO, new SimpleMessage("xyz"), null);
        ReusableLogEventFactory.release(event);
        assertNotNull(event.getContextMap());
        assertNotNull(event.getContextData());
        assertNotNull(event.getContextStack());
    }
