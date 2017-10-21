    public void testWithNameOnly_SystemProperty()
        throws Exception
    {
        Profile profile = newProfile( "prop", null );

        assertActivation( true, profile, newContext( null, newProperties( "prop", "value" ) ) );

        assertActivation( false, profile, newContext( null, newProperties( "prop", "" ) ) );

        assertActivation( false, profile, newContext( null, newProperties( "other", "value" ) ) );
    }
