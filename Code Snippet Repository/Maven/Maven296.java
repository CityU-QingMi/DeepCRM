    @Override
    protected void setUp()
        throws Exception
    {
        super.setUp();
        artifactFactory = lookup( ArtifactFactory.class );
        artifactRepositoryFactory = lookup( ArtifactRepositoryFactory.class );

        RepositorySystemSession repoSession = initRepoSession();
        MavenSession session = new MavenSession( getContainer(), repoSession, new DefaultMavenExecutionRequest(),
                                                 new DefaultMavenExecutionResult() );

        LegacySupport legacySupport = lookup( LegacySupport.class );
        legacySupport.setSession( session );
    }
