    protected void setUp() throws Exception
    {
        super.setUp();

        artifactFactory = (ArtifactFactory) lookup( ArtifactFactory.ROLE );
        conflictResolver = (ConflictResolver) lookup( ConflictResolver.ROLE, roleHint );

        a1 = createArtifact( "a", "1.0" );
        a2 = createArtifact( "a", "2.0" );
        b1 = createArtifact( "b", "1.0" );
    }
