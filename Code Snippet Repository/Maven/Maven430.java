    public void testResolveCompileScopeOverRuntimeScope()
        throws ArtifactResolutionException, InvalidVersionSpecificationException
    {
        ArtifactSpec root = createArtifactSpec( "root", "1.0" );
        ArtifactSpec a = root.addDependency( "a", "1.0" );
        root.addDependency( "c", "3.0", Artifact.SCOPE_RUNTIME );

        a.addDependency( "c", "2.0", Artifact.SCOPE_COMPILE );

        Artifact modifiedC = createArtifactSpec( "c", "3.0", Artifact.SCOPE_COMPILE ).artifact;

        ArtifactResolutionResult res = collect( createSet( new Object[] { root.artifact } ) );
        assertEquals( "Check artifact list", createSet( new Object[] { a.artifact, root.artifact, modifiedC } ),
                      res.getArtifacts() );
        Artifact artifact = getArtifact( "c", res.getArtifacts() );
        assertEquals( "Check artifactScope", Artifact.SCOPE_COMPILE, artifact.getScope() );
    }
