    public void testResolveRangeWithManagedVersion()
        throws ArtifactResolutionException, InvalidVersionSpecificationException
    {
        ArtifactSpec a = createArtifactSpec( "a", "1.0" );
        ArtifactSpec b = a.addDependency( "b", "[1.0,3.0]" );

        ArtifactSpec managedB = createArtifactSpec( "b", "5.0" );

        ArtifactResolutionResult res = collect( a, managedB.artifact );
        assertEquals( "Check artifact list", createSet( new Object[] { a.artifact, managedB.artifact } ),
                      res.getArtifacts() );
        assertEquals( "Check version", "5.0", getArtifact( "b", res.getArtifacts() ).getVersion() );
    }
