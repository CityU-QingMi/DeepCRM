    public void testVersionRangeInclusiveBounds()
        throws Exception
    {
        Profile profile = newProfile( "[1.5,1.6]" );

        assertActivation( false, profile, newContext( null, newProperties( "1.4" ) ) );
        assertActivation( false, profile, newContext( null, newProperties( "1.4.2" ) ) );
        assertActivation( false, profile, newContext( null, newProperties( "1.4.2_09" ) ) );
        assertActivation( false, profile, newContext( null, newProperties( "1.4.2_09-b03" ) ) );

        assertActivation( true, profile, newContext( null, newProperties( "1.5" ) ) );
        assertActivation( true, profile, newContext( null, newProperties( "1.5.0" ) ) );
        assertActivation( true, profile, newContext( null, newProperties( "1.5.0_09" ) ) );
        assertActivation( true, profile, newContext( null, newProperties( "1.5.0_09-b03" ) ) );
        assertActivation( true, profile, newContext( null, newProperties( "1.5.1" ) ) );

        assertActivation( true, profile, newContext( null, newProperties( "1.6" ) ) );
        assertActivation( true, profile, newContext( null, newProperties( "1.6.0" ) ) );
        assertActivation( true, profile, newContext( null, newProperties( "1.6.0_09" ) ) );
        assertActivation( true, profile, newContext( null, newProperties( "1.6.0_09-b03" ) ) );
    }
