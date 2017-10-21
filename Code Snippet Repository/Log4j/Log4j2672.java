    @Test
    public void testLog4jAvroAppenderWithHostsParam() throws IOException {
        final String hosts = String.format("localhost:%s", testPort);
        final FlumeAppender avroAppender = FlumeAppender.createAppender(null,
                null, hosts, "false", "Avro", null, "1000", "1000", "1", "1000",
                "avro", "false", null, null, null, null, null, "true", "1",
                null, null, null, null);
        avroAppender.start();
        avroLogger.addAppender(avroAppender);
        avroLogger.setLevel(Level.ALL);

        Assert.assertNotNull(avroLogger);

        avroLogger.info("Test message");

        final Transaction transaction = channel.getTransaction();
        transaction.begin();

        final Event event = channel.take();
        Assert.assertNotNull(event);
        Assert.assertTrue("Channel contained event, but not expected message",
                getBody(event).endsWith("Test message"));
        transaction.commit();
        transaction.close();

        eventSource.stop();
    }
