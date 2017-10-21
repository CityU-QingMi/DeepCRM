    public void testValidateUniqueRepositoryId()
        throws Exception
    {
        Settings settings = new Settings();
        Profile profile = new Profile();
        profile.setId( "pro" );
        settings.addProfile( profile );
        Repository repo1 = new Repository();
        repo1.setUrl( "http://apache.org/" );
        repo1.setId( "test" );
        profile.addRepository( repo1 );
        Repository repo2 = new Repository();
        repo2.setUrl( "http://apache.org/" );
        repo2.setId( "test" );
        profile.addRepository( repo2 );

        SimpleProblemCollector problems = new SimpleProblemCollector();
        validator.validate( settings, problems );
        assertEquals( 1, problems.messages.size() );
        assertContains( problems.messages.get( 0 ), "'profiles.profile[pro].repositories.repository.id' must be unique"
            + " but found duplicate repository with id test" );
    }
