    @Test
    public void testLevelLogging() {
        org.apache.logging.log4j.Logger logger = context.getLogger("org.apache.logging.log4j.test1");
        logger.log(ExtendedLevels.DETAIL, "Detail message");
        logger.log(Level.DEBUG, "Debug message");
        List<LogEvent> events = list1.getEvents();
        assertNotNull("No events", events);
        assertThat(events, hasSize(1));
        LogEvent event = events.get(0);
        assertEquals("Expected level DETAIL, got" + event.getLevel(), "DETAIL", event.getLevel().name());
        logger = context.getLogger("org.apache.logging.log4j.test2");
        logger.log(ExtendedLevels.NOTE, "Note message");
        logger.log(Level.INFO, "Info message");
        events = list2.getEvents();
        assertNotNull("No events", events);
        assertThat(events, hasSize(1));
        event = events.get(0);
        assertEquals("Expected level NOTE, got" + event.getLevel(), "NOTE", event.getLevel().name());
    }
