    @Test
    public void logger3() {
        logger3.traceEntry();
        logger3.debug(testMarker, "debug message");
        logger3.error("Test Message");
        logger3.info(testMarker, "Info Message");
        logger3.warn("warn Message");
        logger3.traceExit();
        assertThat(app1.getEvents(), hasSize(4));
    }
