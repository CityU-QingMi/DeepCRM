    public void testResolveManagedVersion()
        throws ArtifactResolutionException, InvalidVersionSpecificationException
    {
        ArtifactSpec a = createArtifactSpec( "a", "1.0" );
        a.addDependency( "b", "3.0", Artifact.SCOPE_RUNTIME );

        Artifact managedVersion = createArtifactSpec( "b", "5.0" ).artifact;
        Artifact modifiedB = createArtifactSpec( "b", "5.0", Artifact.SCOPE_RUNTIME ).artifact;

        ArtifactResolutionResult res = collect( a, managedVersion );
        assertEquals( "Check artifact list", createSet( new Object[] { a.artifact, modifiedB } ), res.getArtifacts() );
    }
