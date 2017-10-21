    @Test
    public void logger1() {
        logger1.traceEntry();
        logger1.debug("debug message");
        logger1.error("Test Message");
        logger1.info("Info Message");
        logger1.warn("warn Message");
        logger1.traceExit();
        assertThat(app1.getEvents(), hasSize(6));
        assertThat(app2.getEvents(), hasSize(1));
    }
