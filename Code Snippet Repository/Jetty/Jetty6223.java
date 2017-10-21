    @Test
    public void testAddDuplicate()
    {
        EncoderMetadataSet coders = new EncoderMetadataSet();

        // Add DateEncoder (decodes java.util.Date)
        coders.add(DateEncoder.class);

        try
        {
            // Add TimeEncoder (which also wants to decode java.util.Date)
            coders.add(TimeEncoder.class);
            Assert.fail("Should have thrown IllegalStateException for attempting to register Encoders with duplicate implementation");
        }
        catch (IllegalStateException e)
        {
            Assert.assertThat(e.getMessage(),containsString("Duplicate"));
        }
    }
