    public void testResolveCorrectDependenciesWhenDifferentDependenciesOnNearest()
        throws ArtifactResolutionException, InvalidVersionSpecificationException
    {
        ArtifactSpec a = createArtifactSpec( "a", "1.0" );
        ArtifactSpec b = a.addDependency( "b", "1.0" );
        ArtifactSpec c2 = b.addDependency( "c", "2.0" );
        c2.addDependency( "d", "1.0" );

        ArtifactSpec e = createArtifactSpec( "e", "1.0" );
        ArtifactSpec c1 = e.addDependency( "c", "1.0" );
        ArtifactSpec f = c1.addDependency( "f", "1.0" );

        ArtifactResolutionResult res = collect( createSet( new Object[] { a.artifact, e.artifact } ) );
        assertEquals( "Check artifact list", createSet( new Object[] { a.artifact, b.artifact, e.artifact, c1.artifact,
            f.artifact } ), res.getArtifacts() );
        assertEquals( "Check version", "1.0", getArtifact( "c", res.getArtifacts() ).getVersion() );
    }
