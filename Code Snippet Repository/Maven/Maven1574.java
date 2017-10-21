    public void testWithNegatedNameOnly_UserProperty()
        throws Exception
    {
        Profile profile = newProfile( "!prop", null );

        assertActivation( false, profile, newContext( newProperties( "prop", "value" ), null ) );

        assertActivation( true, profile, newContext( newProperties( "prop", "" ), null ) );

        assertActivation( true, profile, newContext( newProperties( "other", "value" ), null ) );
    }
