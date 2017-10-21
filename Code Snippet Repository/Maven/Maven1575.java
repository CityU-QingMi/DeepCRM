    public void testWithNegatedNameOnly_SystemProperty()
        throws Exception
    {
        Profile profile = newProfile( "!prop", null );

        assertActivation( false, profile, newContext( null, newProperties( "prop", "value" ) ) );

        assertActivation( true, profile, newContext( null, newProperties( "prop", "" ) ) );

        assertActivation( true, profile, newContext( null, newProperties( "other", "value" ) ) );
    }
