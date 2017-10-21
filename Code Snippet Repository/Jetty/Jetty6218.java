    @Test
    public void testAddBadDualDecoders()
    {
        try
        {
            DecoderMetadataSet coders = new DecoderMetadataSet();

            // has duplicated support for the same target Type
            coders.add(BadDualDecoder.class);
            Assert.fail("Should have thrown IllegalStateException for attempting to register Decoders with duplicate implementation");
        }
        catch (IllegalStateException e)
        {
            Assert.assertThat(e.getMessage(),containsString("Duplicate"));
        }
    }
