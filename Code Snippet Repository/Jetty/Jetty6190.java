    @Test
    public void testMessageHandlerBoth() throws DeploymentException
    {
        session.addMessageHandler(new StringWholeHandler());
        session.addMessageHandler(new ByteArrayWholeHandler());
        MessageHandlerWrapper wrapper = session.getMessageHandlerWrapper(MessageType.TEXT);
        Assert.assertThat("Text Handler",wrapper.getHandler(),instanceOf(StringWholeHandler.class));
        Assert.assertEquals("Message Class",wrapper.getMetadata().getMessageClass(),String.class);
        wrapper = session.getMessageHandlerWrapper(MessageType.BINARY);
        Assert.assertThat("Binary Handler",wrapper.getHandler(),instanceOf(ByteArrayWholeHandler.class));
        Assert.assertEquals("Message Class",wrapper.getMetadata().getMessageClass(),byte[].class);
    }
