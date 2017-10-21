    private void assertDecoderType(Class<? extends Decoder> expectedDecoder, MessageType expectedMsgType, Class<?> type)
    {
        PrimitiveDecoderMetadataSet primitives = new PrimitiveDecoderMetadataSet();
        DecoderMetadata metadata = primitives.getMetadataByType(type);
        String prefix = String.format("Metadata By Type [%s]",type.getName());
        Assert.assertThat(prefix,metadata,notNullValue());

        assertClassEquals(prefix + ".coderClass",metadata.getCoderClass(),expectedDecoder);
        Assert.assertThat(prefix + ".messageType",metadata.getMessageType(),is(expectedMsgType));
    }
