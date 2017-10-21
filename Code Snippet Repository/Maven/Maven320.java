    @Override
    protected void setUp()
        throws Exception
    {
        super.setUp();

        artifactResolver = (DefaultArtifactResolver) lookup( ArtifactResolver.class );

        projectArtifact = createLocalArtifact( "project", "3.0" );
    }
