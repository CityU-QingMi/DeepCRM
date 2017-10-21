    @Test
    public void testByteArrayPartial() throws DeploymentException
    {
        List<MessageHandlerMetadata> metadatas = factory.getMetadata(ByteArrayPartialHandler.class);
        Assert.assertThat("Metadata.list.size",metadatas.size(),is(1));

        MessageHandlerMetadata handlerMetadata = metadatas.get(0);
        DecoderMetadata decoderMetadata = decoders.getMetadataFor(handlerMetadata.getMessageClass());
        Assert.assertThat("Message Type",decoderMetadata.getMessageType(),is(MessageType.BINARY));
        Assert.assertThat("Message Class",handlerMetadata.getMessageClass(),is((Type)byte[].class));
    }
