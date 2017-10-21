    public void testResolveNearestNewestIsNearest()
        throws ArtifactResolutionException, InvalidVersionSpecificationException
    {
        ArtifactSpec a = createArtifactSpec( "a", "1.0" );
        ArtifactSpec b = a.addDependency( "b", "1.0" );
        ArtifactSpec c = a.addDependency( "c", "3.0" );

        b.addDependency( "c", "2.0" );

        ArtifactResolutionResult res = collect( a );
        assertEquals( "Check artifact list", createSet( new Object[] { a.artifact, b.artifact, c.artifact } ),
                      res.getArtifacts() );
        assertEquals( "Check version", "3.0", getArtifact( "c", res.getArtifacts() ).getVersion() );
    }
