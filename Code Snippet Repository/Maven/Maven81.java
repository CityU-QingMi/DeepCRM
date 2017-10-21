    @Test
    public void testFileSource()
    {
        try
        {
            new FileSource( null );
            fail( "Should fail, since you must specify a file" );
        }
        catch ( NullPointerException e )
        {
            assertEquals( "file cannot be null", e.getMessage() );
        }
    }
