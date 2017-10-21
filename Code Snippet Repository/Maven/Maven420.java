    public void testCompatibleRanges()
        throws ArtifactResolutionException, InvalidVersionSpecificationException
    {
        ArtifactSpec a = createArtifactSpec( "a", "1.0" );
        ArtifactSpec b = a.addDependency( "b", "1.0" );
        a.addDependency( "c", "[2.0,2.5]" );
        b.addDependency( "c", "[1.0,3.0]" );
        ArtifactSpec c = createArtifactSpec( "c", "2.5" );

        ArtifactResolutionResult res = collect( a );

        assertEquals( "Check artifact list", createSet( new Object[] { a.artifact, b.artifact, c.artifact } ),
                      res.getArtifacts() );
        assertEquals( "Check version", "2.5", getArtifact( "c", res.getArtifacts() ).getVersion() );
    }
