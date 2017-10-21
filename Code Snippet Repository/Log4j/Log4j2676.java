    @Test
    public void testIncompleteBatch2() throws IOException {
        final Agent[] agents = new Agent[] { Agent.createAgent("localhost",
                testPort) };
        final FlumeAppender avroAppender = FlumeAppender.createAppender(agents,
                null, null, "false", "Avro", null, "1000", "1000", "1", "500",
                "avro", "false", null, null, null, null, null, "true", "10",
                null, null, null, null);
        avroAppender.start();
        avroLogger.addAppender(avroAppender);
        avroLogger.setLevel(Level.ALL);

        Assert.assertNotNull(avroLogger);

        avroLogger.info("Test message 0");

        final Transaction transaction = channel.getTransaction();
        transaction.begin();

        avroLogger.info("Test message 1");
        avroLogger.info("Test message 2");
        avroAppender.stop();
        for (int i = 0; i < 3; ++i) {
            final Event event = channel.take();
            Assert.assertNotNull("No event for item " + i, event);
            Assert.assertTrue("Channel contained event, but not expected message. Received : " + getBody(event),
                    getBody(event).endsWith("Test message " + i));
        }
        transaction.commit();
        transaction.close();

        eventSource.stop();
    }
