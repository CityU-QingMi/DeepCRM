    @Test
    public void testUrlSource()
    {
        try
        {
            new UrlSource( null );
            fail( "Should fail, since you must specify a url" );
        }
        catch ( NullPointerException e )
        {
            assertEquals( "url cannot be null", e.getMessage() );
        }
    }
