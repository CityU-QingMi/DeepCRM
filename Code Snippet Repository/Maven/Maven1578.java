    public void testWithNegatedValue_UserProperty()
        throws Exception
    {
        Profile profile = newProfile( "prop", "!value" );

        assertActivation( false, profile, newContext( newProperties( "prop", "value" ), null ) );

        assertActivation( true, profile, newContext( newProperties( "prop", "other" ), null ) );

        assertActivation( true, profile, newContext( newProperties( "prop", "" ), null ) );
    }
