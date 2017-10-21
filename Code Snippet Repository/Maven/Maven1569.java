    public void testWithNegatedValue_SystemProperty()
        throws Exception
    {
        Profile profile = newProfile( "prop", "!value" );

        assertActivation( false, profile, newContext( null, newProperties( "prop", "value" ) ) );

        assertActivation( true, profile, newContext( null, newProperties( "prop", "other" ) ) );

        assertActivation( true, profile, newContext( null, newProperties( "other", "" ) ) );
    }
