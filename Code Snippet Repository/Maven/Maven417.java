    public void testResolveLocalWithNewerVersionButLesserScopeResolvedFirst()
        throws ArtifactResolutionException, InvalidVersionSpecificationException
    {
        ArtifactSpec b = createArtifactSpec( "junit", "3.8.1", Artifact.SCOPE_TEST );
        ArtifactSpec a = createArtifactSpec( "commons-logging", "1.0" );
        a.addDependency( "junit", "3.7" );

        ArtifactResolutionResult res = collect( createSet( new Object[] { a.artifact, b.artifact } ) );
        assertEquals( "Check artifact list", createSet( new Object[] { a.artifact, b.artifact } ), res.getArtifacts() );
        assertEquals( "Check version", "3.8.1", getArtifact( "junit", res.getArtifacts() ).getVersion() );
        assertEquals( "Check artifactScope", Artifact.SCOPE_TEST, getArtifact( "junit", res.getArtifacts() ).getScope() );
    }
