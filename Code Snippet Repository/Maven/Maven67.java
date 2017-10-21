    private void checkInvalidRange( String version )
    {
        try
        {
            VersionRange.createFromVersionSpec( version );
            fail( "Version " + version + " should have failed to construct" );
        }
        catch ( InvalidVersionSpecificationException expected )
        {
            // expected
        }
    }
