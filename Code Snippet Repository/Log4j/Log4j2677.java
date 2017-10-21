    @Test
    public void testBatch() throws IOException {
        final Agent[] agents = new Agent[] { Agent.createAgent("localhost",
                testPort) };
        final FlumeAppender avroAppender = FlumeAppender.createAppender(agents,
                null, null, "false", "Avro", null, "1000", "1000", "1", "1000",
                "avro", "false", null, null, null, null, null, "true", "10",
                null, null, null, null);
        avroAppender.start();
        avroLogger.addAppender(avroAppender);
        avroLogger.setLevel(Level.ALL);

        Assert.assertNotNull(avroLogger);

        for (int i = 0; i < 10; ++i) {
            avroLogger.info("Test message " + i);
        }

        final Transaction transaction = channel.getTransaction();
        transaction.begin();

        for (int i = 0; i < 10; ++i) {
            final Event event = channel.take();
            Assert.assertNotNull("No event for item " + i, event);
            Assert.assertTrue(
                    "Channel contained event, but not expected message",
                    getBody(event).endsWith("Test message " + i));
        }
        transaction.commit();
        transaction.close();

        eventSource.stop();
    }
