    @Test(timeout = DEFAULT_TIMEOUT_MILLIS)
    public void testClientServer() throws Exception {
        final JeroMqAppender appender = ctx.getRequiredAppender(APPENDER_NAME, JeroMqAppender.class);
        final int expectedReceiveCount = 3;
        final JeroMqTestClient client = new JeroMqTestClient(JeroMqManager.getContext(), ENDPOINT, expectedReceiveCount);
        final ExecutorService executor = Executors.newSingleThreadExecutor();
        try {
            final Future<List<String>> future = executor.submit(client);
            Thread.sleep(100);
            final Logger logger = ctx.getLogger(getClass().getName());
            appender.resetSendRcs();
            logger.info("Hello");
            logger.info("Again");
            ThreadContext.put("foo", "bar");
            logger.info("World");
            final List<String> list = future.get();
            Assert.assertEquals(expectedReceiveCount, appender.getSendRcTrue());
            Assert.assertEquals(0, appender.getSendRcFalse());
            Assert.assertEquals("Hello", list.get(0));
            Assert.assertEquals("Again", list.get(1));
            Assert.assertEquals("barWorld", list.get(2));
        } finally {
            executor.shutdown();
        }
    }
