    public void testResolveArtifact()
        throws Exception
    {
        Artifact artifact = new DefaultArtifact( "ut.simple:artifact:1.0" );

        ArtifactRequest artifactRequest = new ArtifactRequest();
        artifactRequest.setArtifact( artifact );
        artifactRequest.addRepository( newTestRepository() );

        ArtifactResult artifactResult = system.resolveArtifact( session, artifactRequest );
        checkArtifactResult( artifactResult, "artifact-1.0.jar" );

        artifact = new DefaultArtifact( "ut.simple:artifact:zip:1.0" );
        artifactRequest.setArtifact( artifact );
        artifactResult = system.resolveArtifact( session, artifactRequest );
        checkArtifactResult( artifactResult, "artifact-1.0.zip" );

        artifact = new DefaultArtifact( "ut.simple:artifact:zip:classifier:1.0" );
        artifactRequest.setArtifact( artifact );
        artifactResult = system.resolveArtifact( session, artifactRequest );
        checkArtifactResult( artifactResult, "artifact-1.0-classifier.zip" );
    }
