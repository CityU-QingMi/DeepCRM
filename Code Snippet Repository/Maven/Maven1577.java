    public void testWithValue_SystemProperty()
        throws Exception
    {
        Profile profile = newProfile( "prop", "value" );

        assertActivation( true, profile, newContext( null, newProperties( "prop", "value" ) ) );

        assertActivation( false, profile, newContext( null, newProperties( "prop", "other" ) ) );

        assertActivation( false, profile, newContext( null, newProperties( "other", "" ) ) );
    }
