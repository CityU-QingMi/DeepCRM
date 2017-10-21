    public void testNonNumericVersionRepresentationReturnsANumberFormatException()
    {
        try
        {
            new DefaultArtifactVersion( "..." );
        }
        catch ( Exception e )
        {
            assertTrue( "We expect a NumberFormatException to be thrown.", e instanceof NumberFormatException );
        }
    }
