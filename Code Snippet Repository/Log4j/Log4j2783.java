    @Test
    public void testSetLevelToNull() throws Exception {
        final Logger childLogger = Logger.getLogger(LOGGER_NAME + ".NullChild");
        assertThat(childLogger.getLevel(), equalTo(Level.FINE));
        assertThat(childLogger.isLoggable(Level.FINE), is(true));
        childLogger.setLevel(Level.SEVERE);
        assertThat(childLogger.getLevel(), equalTo(Level.SEVERE));
        assertThat(childLogger.isLoggable(Level.FINE), is(false));
        // null test
        childLogger.setLevel(null);
        assertThat(childLogger.getLevel(), equalTo(null));
        assertThat(childLogger.isLoggable(Level.FINE), is(true));
        // now go back
        childLogger.setLevel(Level.SEVERE);
        assertThat(childLogger.getLevel(), equalTo(Level.SEVERE));
        assertThat(childLogger.isLoggable(Level.FINE), is(false));
    }
