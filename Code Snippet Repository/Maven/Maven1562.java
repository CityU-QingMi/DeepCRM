    public void testPrefix()
        throws Exception
    {
        Profile profile = newProfile( "1.4" );

        assertActivation( true, profile, newContext( null, newProperties( "1.4" ) ) );
        assertActivation( true, profile, newContext( null, newProperties( "1.4.2" ) ) );
        assertActivation( true, profile, newContext( null, newProperties( "1.4.2_09" ) ) );
        assertActivation( true, profile, newContext( null, newProperties( "1.4.2_09-b03" ) ) );

        assertActivation( false, profile, newContext( null, newProperties( "1.3" ) ) );

        assertActivation( false, profile, newContext( null, newProperties( "1.5" ) ) );
    }
