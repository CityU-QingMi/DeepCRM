    public void testWithValue_UserProperty()
        throws Exception
    {
        Profile profile = newProfile( "prop", "value" );

        assertActivation( true, profile, newContext( newProperties( "prop", "value" ), null ) );

        assertActivation( false, profile, newContext( newProperties( "prop", "other" ), null ) );

        assertActivation( false, profile, newContext( newProperties( "prop", "" ), null ) );
    }
