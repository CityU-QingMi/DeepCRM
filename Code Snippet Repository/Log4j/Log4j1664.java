    @Test
    public void catching() {
        try {
            throw new NullPointerException();
        } catch (final Exception e) {
            logger.catching(e);
        }
        final List<LogEvent> events = app.getEvents();
        assertEquals("Incorrect number of events. Expected 1, actual " + events.size(), 1, events.size());
    }
