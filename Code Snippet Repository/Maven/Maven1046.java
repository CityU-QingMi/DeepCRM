    public void testPluginRepositoryInjection()
        throws Exception
    {
        MavenExecutionRequest request = new DefaultMavenExecutionRequest();

        Repository r = new Repository();
        r.setId( "test" );
        r.setUrl( "file:///test" );

        Profile p = new Profile();
        p.setId( "test" );
        p.addPluginRepository( r );

        Settings settings = new Settings();
        settings.addProfile( p );
        settings.addActiveProfile( p.getId() );

        testee.populateFromSettings( request, settings );

        List<ArtifactRepository> repositories = request.getPluginArtifactRepositories();
        assertEquals( 1, repositories.size() );
        assertEquals( r.getId(), repositories.get( 0 ).getId() );
        assertEquals( r.getUrl(), repositories.get( 0 ).getUrl() );
    }
