    @Test
    public void testStartIdentity() throws Exception
    {
        ExtensionStack stack = createExtensionStack();
        try
        {
            // 1 extension
            List<ExtensionConfig> configs = new ArrayList<>();
            configs.add(ExtensionConfig.parse("identity"));
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
            Extension actualIncomingExtension = assertIsExtension("Incoming",stack.getNextIncoming(),IdentityExtension.class);
            Extension actualOutgoingExtension = assertIsExtension("Outgoing",stack.getNextOutgoing(),IdentityExtension.class);
            Assert.assertEquals(actualIncomingExtension,actualOutgoingExtension);
        }
        finally
        {
            stack.stop();
        }
    }
