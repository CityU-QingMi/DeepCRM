    @Test
    public void noInterpolation() {
        final Logger logger = Logger.getLogger("Test");
        logger.info("{raw}");
        logger.log(new LogRecord(INFO, "{raw}"));// should lead to the same as previous but was not the case LOG4J2-1251
        final List<LogEvent> events = ListAppender.getListAppender("TestAppender").getEvents();
        assertThat(events, hasSize(2));
        assertEquals("{raw}", events.get(0).getMessage().getFormattedMessage());
        assertEquals("{raw}", events.get(1).getMessage().getFormattedMessage());
    }
