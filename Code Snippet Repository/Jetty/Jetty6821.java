    @Test
    public void testStartIdentityTwice() throws Exception
    {
        ExtensionStack stack = createExtensionStack();
        try
        {
            // 1 extension
            List<ExtensionConfig> configs = new ArrayList<>();
            configs.add(ExtensionConfig.parse("identity; id=A"));
            configs.add(ExtensionConfig.parse("identity; id=B"));
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
            IdentityExtension actualIncomingExtension = assertIsExtension("Incoming",stack.getNextIncoming(),IdentityExtension.class);
            IdentityExtension actualOutgoingExtension = assertIsExtension("Outgoing",stack.getNextOutgoing(),IdentityExtension.class);

            Assert.assertThat("Incoming[identity].id",actualIncomingExtension.getParam("id"),is("A"));
            Assert.assertThat("Outgoing[identity].id",actualOutgoingExtension.getParam("id"),is("B"));
        }
        finally
        {
            stack.stop();
        }
    }
