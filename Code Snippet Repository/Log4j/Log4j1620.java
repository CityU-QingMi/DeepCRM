    @Test
    public void logger2() {
        logger2.traceEntry();
        logger2.debug("debug message");
        logger2.error("Test Message");
        logger2.info("Info Message");
        logger2.warn("warn Message");
        logger2.traceExit();
        assertThat(app1.getEvents(), hasSize(2));
        assertThat(app2.getEvents(), hasSize(4));
    }
