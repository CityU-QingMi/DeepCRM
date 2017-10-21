    @Test
    public void testAddGetMetadataByImpl()
    {
        DecoderMetadataSet coders = new DecoderMetadataSet();

        coders.add(IntegerDecoder.class);
        List<DecoderMetadata> metadatas = coders.getMetadataByImplementation(IntegerDecoder.class);
        Assert.assertThat("Metadatas (by impl) count",metadatas.size(),is(1));
        DecoderMetadata metadata = metadatas.get(0);
        assertMetadata(metadata,Integer.class,IntegerDecoder.class,MessageType.TEXT);
    }
