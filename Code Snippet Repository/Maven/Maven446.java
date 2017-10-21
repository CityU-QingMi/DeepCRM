    public void testResolveWithFilter()
        throws ArtifactResolutionException, InvalidVersionSpecificationException
    {
        ArtifactSpec a = createArtifactSpec( "a", "1.0" );
        ArtifactSpec b = a.addDependency( "b", "1.0" );
        ArtifactSpec c = a.addDependency( "c", "3.0" );

        b.addDependency( "c", "2.0" );
        ArtifactSpec d = b.addDependency( "d", "4.0" );

        ArtifactResolutionResult res = collect( a );
        assertEquals( "Check artifact list",
                      createSet( new Object[] { a.artifact, b.artifact, c.artifact, d.artifact } ), res.getArtifacts() );

        ArtifactFilter filter = new ExclusionSetFilter( new String[] { "b" } );
        res = collect( a, filter );
        assertEquals( "Check artifact list", createSet( new Object[] { a.artifact, c.artifact } ), res.getArtifacts() );
    }
