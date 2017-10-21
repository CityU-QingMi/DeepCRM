    @Test
    public void testConnectionRefused() {
        final Agent[] agents = new Agent[] { Agent.createAgent("localhost",
                testPort) };
        final FlumeAppender avroAppender = FlumeAppender.createAppender(agents,
                null, null, "false", "Avro", null, "1000", "1000", "1", "1000",
                "avro", "false", null, null, null, null, null, "true", "1",
                null, null, null, null);
        avroAppender.start();
        avroLogger.addAppender(avroAppender);
        avroLogger.setLevel(Level.ALL);
        eventSource.stop();

        boolean caughtException = false;

        try {
            avroLogger.info("message 1");
        } catch (final Throwable t) {
            // logger.debug("Logging to a non-existent server failed (as expected)",
            // t);

            caughtException = true;
        }

        Assert.assertTrue(caughtException);
    }
