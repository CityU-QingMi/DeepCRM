    public void testValidateUniqueProfileId()
        throws Exception
    {
        Settings settings = new Settings();
        Profile profile1 = new Profile();
        profile1.setId( "test" );
        settings.addProfile( profile1 );
        Profile profile2 = new Profile();
        profile2.setId( "test" );
        settings.addProfile( profile2 );

        SimpleProblemCollector problems = new SimpleProblemCollector();
        validator.validate( settings, problems );
        assertEquals( 1, problems.messages.size() );
        assertContains( problems.messages.get( 0 ),
                        "'profiles.profile.id' must be unique but found duplicate profile with id test" );
    }
