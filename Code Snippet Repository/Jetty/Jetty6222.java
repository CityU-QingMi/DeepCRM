    @Test
    public void testAddBadDualEncoders()
    {
        try
        {
            EncoderMetadataSet coders = new EncoderMetadataSet();

            // has duplicated support for the same target Type
            coders.add(BadDualEncoder.class);
            Assert.fail("Should have thrown IllegalStateException for attempting to register Encoders with duplicate implementation");
        }
        catch (IllegalStateException e)
        {
            Assert.assertThat(e.getMessage(),containsString("Duplicate"));
        }
    }
