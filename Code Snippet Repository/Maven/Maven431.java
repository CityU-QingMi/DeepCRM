    public void testResolveCompileScopeOverProvidedScope()
        throws ArtifactResolutionException, InvalidVersionSpecificationException
    {
        ArtifactSpec a = createArtifactSpec( "a", "1.0" );
        ArtifactSpec c = createArtifactSpec( "c", "3.0", Artifact.SCOPE_PROVIDED );

        a.addDependency( "c", "2.0", Artifact.SCOPE_COMPILE );

        Artifact modifiedC = createArtifactSpec( "c", "3.0", Artifact.SCOPE_COMPILE ).artifact;

        ArtifactResolutionResult res = collect( createSet( new Object[] { a.artifact, c.artifact } ) );
        assertEquals( "Check artifact list", createSet( new Object[] { a.artifact, modifiedC } ), res.getArtifacts() );
        Artifact artifact = getArtifact( "c", res.getArtifacts() );
        // local wins now, and irrelevant if not local as test/provided aren't transitive
        // assertEquals( "Check artifactScope", Artifact.SCOPE_COMPILE, artifact.getArtifactScope() );
        assertEquals( "Check artifactScope", Artifact.SCOPE_PROVIDED, artifact.getScope() );
    }
