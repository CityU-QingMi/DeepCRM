    public void testResolveArtifacts()
        throws Exception
    {
        ArtifactRequest req1 = new ArtifactRequest();
        req1.setArtifact( new DefaultArtifact( "ut.simple:artifact:1.0" ) );
        req1.addRepository( newTestRepository() );

        ArtifactRequest req2 = new ArtifactRequest();
        req2.setArtifact( new DefaultArtifact( "ut.simple:artifact:zip:1.0" ) );
        req2.addRepository( newTestRepository() );

        ArtifactRequest req3 = new ArtifactRequest();
        req3.setArtifact( new DefaultArtifact( "ut.simple:artifact:zip:classifier:1.0" ) );
        req3.addRepository( newTestRepository() );

        List<ArtifactRequest> requests = Arrays.asList( req1, req2, req3 );

        List<ArtifactResult> results = system.resolveArtifacts( session, requests );

        assertEquals( 3, results.size() );
        checkArtifactResult( results.get( 0 ), "artifact-1.0.jar" );
        checkArtifactResult( results.get( 1 ), "artifact-1.0.zip" );
        checkArtifactResult( results.get( 2 ), "artifact-1.0-classifier.zip" );
    }
