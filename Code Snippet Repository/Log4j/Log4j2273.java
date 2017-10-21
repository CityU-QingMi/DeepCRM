    @Test
    public void testSocket() throws Exception {
        TestSocketServer testServer;
        ExecutorService executor = null;
        Future<InputStream> futureIn;

        try {
            executor = Executors.newSingleThreadExecutor();
            System.err.println("Initializing server");
            testServer = new TestSocketServer();
            futureIn = executor.submit(testServer);

            //System.err.println("Initializing logger");
            final Logger logger = context.getLogger();

            String message = "Log #1";
            logger.error(message);

            final BufferedReader reader = new BufferedReader(new InputStreamReader(futureIn.get()));
            assertEquals(message, reader.readLine());

            //System.err.println("Closing server");
            closeQuietly(testServer);
            assertTrue("Server not shutdown", testServer.server.isClosed());

            //System.err.println("Sleeping to ensure no race conditions");
            Thread.sleep(1000);

            message = "Log #2";
            try {
                logger.error(message);
                fail("Expected exception not thrown");
            } catch (final AppenderLoggingException e) {
                // An exception is expected.
            }

            message = "Log #3";
            try {
                logger.error(message);
                fail("Expected exception not thrown");
            } catch (final AppenderLoggingException e) {
                // An exception is expected.
            }
        } finally {
            closeQuietly(executor);
        }
    }
