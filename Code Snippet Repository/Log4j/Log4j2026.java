    @Test
    public void testEmptyAttribute() throws Exception {
        final Logger logger = LogManager.getLogger();
        logger.info("Test");
        final StatusData data = StatusLogger.getLogger().getStatusData().get(0);
        //System.out.println(data.getFormattedStatus());

        assertEquals(Level.ERROR, data.getLevel());
        assertTrue(data.getMessage().getFormattedMessage().contains("multiple root loggers"));
    }
