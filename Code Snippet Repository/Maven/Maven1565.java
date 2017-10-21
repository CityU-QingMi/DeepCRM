    public void testVersionRangeExclusiveBounds()
        throws Exception
    {
        Profile profile = newProfile( "(1.3,1.6)" );

        assertActivation( false, profile, newContext( null, newProperties( "1.3" ) ) );
        assertActivation( false, profile, newContext( null, newProperties( "1.3.0" ) ) );
        assertActivation( false, profile, newContext( null, newProperties( "1.3.0_09" ) ) );
        assertActivation( false, profile, newContext( null, newProperties( "1.3.0_09-b03" ) ) );

        assertActivation( true, profile, newContext( null, newProperties( "1.3.1" ) ) );
        assertActivation( true, profile, newContext( null, newProperties( "1.3.1_09" ) ) );
        assertActivation( true, profile, newContext( null, newProperties( "1.3.1_09-b03" ) ) );

        assertActivation( true, profile, newContext( null, newProperties( "1.5" ) ) );
        assertActivation( true, profile, newContext( null, newProperties( "1.5.0" ) ) );
        assertActivation( true, profile, newContext( null, newProperties( "1.5.0_09" ) ) );
        assertActivation( true, profile, newContext( null, newProperties( "1.5.0_09-b03" ) ) );
        assertActivation( true, profile, newContext( null, newProperties( "1.5.1" ) ) );

        assertActivation( false, profile, newContext( null, newProperties( "1.6" ) ) );
    }
