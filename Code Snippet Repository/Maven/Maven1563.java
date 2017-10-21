    public void testPrefixNegated()
        throws Exception
    {
        Profile profile = newProfile( "!1.4" );

        assertActivation( false, profile, newContext( null, newProperties( "1.4" ) ) );
        assertActivation( false, profile, newContext( null, newProperties( "1.4.2" ) ) );
        assertActivation( false, profile, newContext( null, newProperties( "1.4.2_09" ) ) );
        assertActivation( false, profile, newContext( null, newProperties( "1.4.2_09-b03" ) ) );

        assertActivation( true, profile, newContext( null, newProperties( "1.3" ) ) );

        assertActivation( true, profile, newContext( null, newProperties( "1.5" ) ) );
    }
