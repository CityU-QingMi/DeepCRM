    @Test
    public void testSetLevel() throws Exception {
        final Logger childLogger = Logger.getLogger(LOGGER_NAME + ".Child");
        assertThat(childLogger.getLevel(), equalTo(Level.FINE));
        logger.setLevel(Level.SEVERE);
        assertThat(childLogger.getLevel(), equalTo(Level.FINE));
        assertThat(logger.getLevel(), equalTo(Level.SEVERE));
        logger.setLevel(Level.FINER);
        assertThat(logger.getLevel(), equalTo(Level.FINER));
        logger.setLevel(Level.FINE);
        assertThat(logger.getLevel(), equalTo(Level.FINE));
        assertThat(childLogger.getLevel(), equalTo(Level.FINE));
        assertThat(childLogger.isLoggable(Level.ALL), is(false));
    }
