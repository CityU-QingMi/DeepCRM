    @Test
    public void testAddGetMetadataByImpl()
    {
        EncoderMetadataSet coders = new EncoderMetadataSet();

        coders.add(IntegerEncoder.class);
        List<EncoderMetadata> metadatas = coders.getMetadataByImplementation(IntegerEncoder.class);
        Assert.assertThat("Metadatas (by impl) count",metadatas.size(),is(1));
        EncoderMetadata metadata = metadatas.get(0);
        assertMetadata(metadata,Integer.class,IntegerEncoder.class,MessageType.TEXT);
    }
