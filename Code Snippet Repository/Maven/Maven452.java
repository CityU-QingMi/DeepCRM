    public void disabledtestResolveCorrectDependenciesWhenDifferentDependenciesOnNewest()
        throws ArtifactResolutionException, InvalidVersionSpecificationException
    {
        // TODO use newest conflict resolver
        ArtifactSpec a = createArtifactSpec( "a", "1.0" );
        ArtifactSpec b = a.addDependency( "b", "1.0" );
        ArtifactSpec c2 = b.addDependency( "c", "2.0" );
        ArtifactSpec d = c2.addDependency( "d", "1.0" );

        ArtifactSpec e = createArtifactSpec( "e", "1.0" );
        ArtifactSpec c1 = e.addDependency( "c", "1.0" );
        c1.addDependency( "f", "1.0" );

        ArtifactResolutionResult res = collect( createSet( new Object[] { a.artifact, e.artifact } ) );
        assertEquals( "Check artifact list", createSet( new Object[] { a.artifact, b.artifact, e.artifact, c2.artifact,
            d.artifact } ), res.getArtifacts() );
        assertEquals( "Check version", "2.0", getArtifact( "c", res.getArtifacts() ).getVersion() );
    }
