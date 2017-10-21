    @Test
    public void testStartNothing() throws Exception
    {
        ExtensionStack stack = createExtensionStack();
        try
        {
            // intentionally empty
            List<ExtensionConfig> configs = new ArrayList<>();
            stack.negotiate(configs);

            // Setup Listeners
            DummyIncomingFrames session = new DummyIncomingFrames("Session");
            DummyOutgoingFrames connection = new DummyOutgoingFrames("Connection");
            stack.setNextOutgoing(connection);
            stack.setNextIncoming(session);

            // Start
            stack.start();

            // Dump
            LOG.debug("{}",stack.dump());

            // Should be no change to handlers
            Assert.assertEquals("Incoming Handler",stack.getNextIncoming(),session);
            Assert.assertEquals("Outgoing Handler",stack.getNextOutgoing(),connection);
        }
        finally
        {
            stack.stop();
        }
    }
