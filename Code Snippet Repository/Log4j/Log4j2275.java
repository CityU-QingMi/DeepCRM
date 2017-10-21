    @Test
    public void testConnect() throws Exception {
        // TODO: there's a JUnit rule that simplifies this (matt)
        System.err.println("Initializing logger");
        Logger logger = null;
        try {
            logger = context.getLogger();
        } catch (final NullPointerException e) {
            fail("Unexpected exception; should not occur until first logging statement " + e.getMessage());
        }

        final String message = "Log #1";
        try {
            logger.error(message);
            fail("Expected exception not thrown");
        } catch (final AppenderLoggingException e) {
            //System.err.println("Expected exception here, but already errored out when initializing logger");
        }
    }
