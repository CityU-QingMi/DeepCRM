    public void testTransitiveResolutionWhereAllArtifactsAreNotPresentInTheLocalRepositoryAndMustBeRetrievedFromTheRemoteRepository()
        throws Exception
    {
        Artifact i = createRemoteArtifact( "i", "1.0-SNAPSHOT" );
        deleteLocalArtifact( i );

        Artifact j = createRemoteArtifact( "j", "1.0-SNAPSHOT" );
        deleteLocalArtifact( j );

        ArtifactResolutionResult result = artifactResolver.resolveTransitively( Collections.singleton( i ), projectArtifact, remoteRepositories(), localRepository(), null );

        printErrors( result );

        assertEquals( 2, result.getArtifacts().size() );

        assertTrue( result.getArtifacts().contains( i ) );

        assertTrue( result.getArtifacts().contains( j ) );

        assertLocalArtifactPresent( i );

        assertLocalArtifactPresent( j );
    }
