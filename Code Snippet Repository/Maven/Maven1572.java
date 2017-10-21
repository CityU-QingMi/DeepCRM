    public void testWithNameOnly_UserProperty()
        throws Exception
    {
        Profile profile = newProfile( "prop", null );

        assertActivation( true, profile, newContext( newProperties( "prop", "value" ), null ) );

        assertActivation( false, profile, newContext( newProperties( "prop", "" ), null ) );

        assertActivation( false, profile, newContext( newProperties( "other", "value" ), null ) );
    }
