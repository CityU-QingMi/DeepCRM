    @Test
    public void testMessageHandlerReplaceTextHandler() throws DeploymentException
    {
        MessageHandler oldText = new StringWholeHandler();
        session.addMessageHandler(oldText); // add a TEXT handler
        session.addMessageHandler(new ByteArrayWholeHandler()); // add BINARY handler
        session.removeMessageHandler(oldText); // remove original TEXT handler
        session.addMessageHandler(new LongMessageHandler()); // add new TEXT handler
        MessageHandlerWrapper wrapper = session.getMessageHandlerWrapper(MessageType.BINARY);
        Assert.assertThat("Binary Handler",wrapper.getHandler(),instanceOf(ByteArrayWholeHandler.class));
        Assert.assertEquals("Message Class",wrapper.getMetadata().getMessageClass(),byte[].class);
        wrapper = session.getMessageHandlerWrapper(MessageType.TEXT);
        Assert.assertThat("Text Handler",wrapper.getHandler(),instanceOf(LongMessageHandler.class));
        Assert.assertEquals("Message Class",wrapper.getMetadata().getMessageClass(),Long.class);
    }
