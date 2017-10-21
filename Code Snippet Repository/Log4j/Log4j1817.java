    @Test(timeout = DEFAULT_TIMEOUT_MILLIS)
    public void testServerOnly() throws Exception {
        final Logger logger = ctx.getLogger(getClass().getName());
        final JeroMqAppender appender = ctx.getRequiredAppender(APPENDER_NAME, JeroMqAppender.class);
        appender.resetSendRcs();
        logger.info("Hello");
        logger.info("Again");
        Assert.assertEquals(2, appender.getSendRcTrue());
        Assert.assertEquals(0, appender.getSendRcFalse());
    }
