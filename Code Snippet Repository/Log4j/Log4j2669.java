    @Test
    public void testNotConnected() throws Exception {
        eventSource.stop();
        final String altPort = Integer.toString(Integer.parseInt(testPort) + 1);
        final Agent[] agents = new Agent[] {
                Agent.createAgent("localhost", testPort),
                Agent.createAgent("localhost", altPort) };
        final FlumeAppender avroAppender = FlumeAppender.createAppender(agents,
                null, null, "false", "Avro", null, "1000", "1000", "1", "1000",
                "avro", "false", null, null, null, null, null, "true", "1",
                null, null, null, null);
        avroAppender.start();
        Assert.assertTrue("Appender Not started", avroAppender.isStarted());
        avroLogger.addAppender(avroAppender);
        avroLogger.setLevel(Level.ALL);

        try {
            avroLogger.info("Test message");
            Assert.fail("Exception should have been thrown");
        } catch (final Exception ex) {

        }

        try {
            final Context context = new Context();
            context.put("port", altPort);
            context.put("bind", "0.0.0.0");

            Configurables.configure(eventSource, context);

            eventSource.start();
        } catch (final ChannelException e) {
            Assert.fail("Caught exception while resetting port to " + altPort
                    + " : " + e.getMessage());
        }

        avroLogger.info("Test message 2");

        final Transaction transaction = channel.getTransaction();
        transaction.begin();

        final Event event = channel.take();
        Assert.assertNotNull(event);
        Assert.assertTrue("Channel contained event, but not expected message",
                getBody(event).endsWith("Test message 2"));
        transaction.commit();
        transaction.close();
    }
