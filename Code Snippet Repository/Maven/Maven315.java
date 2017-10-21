    public void testTransitiveResolutionWhereAllArtifactsArePresentInTheLocalRepository()
        throws Exception
    {
        Artifact g = createLocalArtifact( "g", "1.0" );

        Artifact h = createLocalArtifact( "h", "1.0" );

        ArtifactResolutionResult result = artifactResolver.resolveTransitively( Collections.singleton( g ), projectArtifact, remoteRepositories(), localRepository(), null );

        printErrors( result );

        assertEquals( 2, result.getArtifacts().size() );

        assertTrue( result.getArtifacts().contains( g ) );

        assertTrue( result.getArtifacts().contains( h ) );

        assertLocalArtifactPresent( g );

        assertLocalArtifactPresent( h );
    }
