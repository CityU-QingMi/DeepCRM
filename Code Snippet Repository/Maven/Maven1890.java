    public void testValidate()
    {
        Settings model = new Settings();
        Profile prof = new Profile();
        prof.setId( "xxx" );
        model.addProfile( prof );
        SimpleProblemCollector problems = new SimpleProblemCollector();
        validator.validate( model, problems );
        assertEquals( 0, problems.messages.size() );

        Repository repo = new Repository();
        prof.addRepository( repo );
        problems = new SimpleProblemCollector();
        validator.validate( model, problems );
        assertEquals( 2, problems.messages.size() );

        repo.setUrl( "http://xxx.xxx.com" );
        problems = new SimpleProblemCollector();
        validator.validate( model, problems );
        assertEquals( 1, problems.messages.size() );

        repo.setId( "xxx" );
        problems = new SimpleProblemCollector();
        validator.validate( model, problems );
        assertEquals( 0, problems.messages.size() );
    }
