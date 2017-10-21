    @Test(timeout = DEFAULT_TIMEOUT_MILLIS)
    public void testMultiThreadedServer() throws Exception {
        final int nThreads = 10;
        final JeroMqAppender appender = ctx.getRequiredAppender(APPENDER_NAME, JeroMqAppender.class);
        final int expectedReceiveCount = 2 * nThreads;
        final JeroMqTestClient client = new JeroMqTestClient(JeroMqManager.getContext(), ENDPOINT,
                expectedReceiveCount);
        final ExecutorService executor = Executors.newSingleThreadExecutor();
        try {
            final Future<List<String>> future = executor.submit(client);
            Thread.sleep(100);
            final Logger logger = ctx.getLogger(getClass().getName());
            appender.resetSendRcs();
            final ExecutorService fixedThreadPool = Executors.newFixedThreadPool(nThreads);
            for (int i = 0; i < 10.; i++) {
                fixedThreadPool.submit(new Runnable() {
                    @Override
                    public void run() {
                        logger.info("Hello");
                        logger.info("Again");
                    }
                });
            }
            final List<String> list = future.get();
            Assert.assertEquals(expectedReceiveCount, appender.getSendRcTrue());
            Assert.assertEquals(0, appender.getSendRcFalse());
            int hello = 0;
            int again = 0;
            for (final String string : list) {
                switch (string) {
                case "Hello":
                    hello++;
                    break;
                case "Again":
                    again++;
                    break;
                default:
                    Assert.fail("Unexpected message: " + string);
                }
            }
            Assert.assertEquals(nThreads, hello);
            Assert.assertEquals(nThreads, again);
        } finally {
            ExecutorServices.shutdown(executor, DEFAULT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS,
                    JeroMqAppenderTest.class.getSimpleName());
        }
    }
