    @Test
    public void testAddValidDualDecoders()
    {
        DecoderMetadataSet coders = new DecoderMetadataSet();

        coders.add(ValidDualDecoder.class);

        List<Class<? extends Decoder>> decodersList = coders.getList();
        Assert.assertThat("Decoder List",decodersList,notNullValue());
        Assert.assertThat("Decoder List count",decodersList.size(),is(2));

        DecoderMetadata metadata;
        metadata = coders.getMetadataByType(Integer.class);
        assertMetadata(metadata,Integer.class,ValidDualDecoder.class,MessageType.TEXT);

        metadata = coders.getMetadataByType(Long.class);
        assertMetadata(metadata,Long.class,ValidDualDecoder.class,MessageType.BINARY);
    }
