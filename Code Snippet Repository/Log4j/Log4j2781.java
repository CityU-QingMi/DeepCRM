    @Test
    public void testRootSetLevelToNull() throws Exception {
        final Logger rootLogger = Logger.getLogger(Strings.EMPTY);
        assertThat(rootLogger.getLevel(), equalTo(Level.SEVERE));
        assertThat(rootLogger.isLoggable(Level.SEVERE), is(true));
        // null test
        rootLogger.setLevel(null);
        assertThat(rootLogger.getLevel(), equalTo(null));
        assertThat(rootLogger.isLoggable(Level.SEVERE), is(true));
        // now go back to a different one
        rootLogger.setLevel(Level.INFO);
        assertThat(rootLogger.getLevel(), equalTo(Level.INFO));
        assertThat(rootLogger.isLoggable(Level.FINE), is(false));
    }
