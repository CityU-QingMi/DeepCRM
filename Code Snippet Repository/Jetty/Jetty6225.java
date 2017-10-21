    @Test
    public void testAddValidDualEncoders()
    {
        EncoderMetadataSet coders = new EncoderMetadataSet();

        coders.add(ValidDualEncoder.class);

        List<Class<? extends Encoder>> EncodersList = coders.getList();
        Assert.assertThat("Encoder List",EncodersList,notNullValue());
        Assert.assertThat("Encoder List count",EncodersList.size(),is(2));

        EncoderMetadata metadata;
        metadata = coders.getMetadataByType(Integer.class);
        assertMetadata(metadata,Integer.class,ValidDualEncoder.class,MessageType.TEXT);

        metadata = coders.getMetadataByType(Long.class);
        assertMetadata(metadata,Long.class,ValidDualEncoder.class,MessageType.BINARY);
    }
