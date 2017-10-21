    public void testValidateRepository()
        throws Exception
    {
        Profile profile = new Profile();
        Repository repo = new Repository();
        repo.setId( "local" );
        profile.addRepository( repo );
        repo = new Repository();
        repo.setId( "illegal\\:/chars" );
        repo.setUrl( "http://void" );
        profile.addRepository( repo );
        Settings settings = new Settings();
        settings.addProfile( profile );

        SimpleProblemCollector problems = new SimpleProblemCollector();
        validator.validate( settings, problems );
        assertEquals( 3, problems.messages.size() );
        assertContains( problems.messages.get( 0 ),
                        "'profiles.profile[default].repositories.repository.id' must not be 'local'" );
        assertContains( problems.messages.get( 1 ),
                        "'profiles.profile[default].repositories.repository.url' for local is missing" );
        assertContains( problems.messages.get( 2 ),
                        "'profiles.profile[default].repositories.repository.id' must not contain any of these characters" );
    }
