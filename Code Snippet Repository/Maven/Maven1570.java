    public void testWithValue_UserPropertyDominantOverSystemProperty()
        throws Exception
    {
        Profile profile = newProfile( "prop", "value" );

        Properties props1 = newProperties( "prop", "value" );
        Properties props2 = newProperties( "prop", "other" );

        assertActivation( true, profile, newContext( props1, props2 ) );

        assertActivation( false, profile, newContext( props2, props1 ) );
    }
