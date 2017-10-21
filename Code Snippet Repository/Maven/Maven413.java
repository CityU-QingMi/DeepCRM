    @Override
    protected void setUp()
        throws Exception
    {
        super.setUp();

        source = new Source();
        artifactFactory = (ArtifactFactory) lookup( ArtifactFactory.ROLE );
        artifactCollector = lookup( LegacyArtifactCollector.class );

        projectArtifact = createArtifactSpec( "project", "1.0", null );
    }
