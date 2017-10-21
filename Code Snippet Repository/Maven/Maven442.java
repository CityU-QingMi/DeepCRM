    public void testTestScopeNotTransitive()
        throws ArtifactResolutionException, InvalidVersionSpecificationException
    {
        ArtifactSpec a = createArtifactSpec( "a", "1.0", Artifact.SCOPE_TEST );
        ArtifactSpec b = createArtifactSpec( "b", "1.0" );
        b.addDependency( "c", "3.0", Artifact.SCOPE_TEST );

        ArtifactResolutionResult res = collect( createSet( new Object[] { a.artifact, b.artifact } ) );
        assertEquals( "Check artifact list", createSet( new Object[] { a.artifact, b.artifact } ), res.getArtifacts() );
    }
