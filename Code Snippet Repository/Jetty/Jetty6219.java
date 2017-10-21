    @Test
    public void testAddDuplicate()
    {
        DecoderMetadataSet coders = new DecoderMetadataSet();

        // Add DateDecoder (decodes java.util.Date)
        coders.add(DateDecoder.class);

        try
        {
            // Add TimeDecoder (which also wants to decode java.util.Date)
            coders.add(TimeDecoder.class);
            Assert.fail("Should have thrown IllegalStateException for attempting to register Decoders with duplicate implementation");
        }
        catch (IllegalStateException e)
        {
            Assert.assertThat(e.getMessage(),containsString("Duplicate"));
        }
    }
