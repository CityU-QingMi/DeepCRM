    @Test
    public void testStructured() throws IOException {
        final Agent[] agents = new Agent[] { Agent.createAgent("localhost",
                testPort) };
        final FlumeAppender avroAppender = FlumeAppender.createAppender(agents,
                null, null, "false", "Avro", null, "1000", "1000", "1", "1000",
                "avro", "false", null, null, null, "ReqCtx_", null, "true",
                "1", null, null, null, null);
        avroAppender.start();
        final Logger eventLogger = (Logger) LogManager.getLogger("EventLogger");
        Assert.assertNotNull(eventLogger);
        eventLogger.addAppender(avroAppender);
        eventLogger.setLevel(Level.ALL);

        final StructuredDataMessage msg = new StructuredDataMessage("Transfer",
                "Success", "Audit");
        msg.put("memo", "This is a memo");
        msg.put("acct", "12345");
        msg.put("amount", "100.00");
        ThreadContext.put("id", UUID.randomUUID().toString());
        ThreadContext.put("memo", null);
        ThreadContext.put("test", "123");

        EventLogger.logEvent(msg);

        final Transaction transaction = channel.getTransaction();
        transaction.begin();

        final Event event = channel.take();
        Assert.assertNotNull(event);
        Assert.assertTrue("Channel contained event, but not expected message", getBody(event).endsWith("Success"));
        transaction.commit();
        transaction.close();

        eventSource.stop();
        eventLogger.removeAppender(avroAppender);
        avroAppender.stop();
    }
