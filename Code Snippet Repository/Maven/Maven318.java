    public void testResolutionOfAnArtifactWhereOneRemoteRepositoryIsBadButOneIsGood()
        throws Exception
    {
        Artifact l = createRemoteArtifact( "l", "1.0-SNAPSHOT" );
        deleteLocalArtifact( l );

        List<ArtifactRepository> repositories = new ArrayList<>();
        repositories.add( remoteRepository() );
        repositories.add( badRemoteRepository() );

        artifactResolver.resolve( l, repositories, localRepository() );

        assertLocalArtifactPresent( l );
    }
