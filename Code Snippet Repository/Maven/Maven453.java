    public void disabledtestResolveCorrectDependenciesWhenDifferentDependenciesOnNewestVersionReplaced()
        throws ArtifactResolutionException, InvalidVersionSpecificationException
    {
        // TODO use newest conflict resolver
        ArtifactSpec a = createArtifactSpec( "a", "1.0" );
        ArtifactSpec b1 = a.addDependency( "b", "1.0" );
        ArtifactSpec c = a.addDependency( "c", "1.0" );
        ArtifactSpec d2 = b1.addDependency( "d", "2.0" );
        d2.addDependency( "h", "1.0" );
        ArtifactSpec d1 = c.addDependency( "d", "1.0" );
        ArtifactSpec b2 = c.addDependency( "b", "2.0" );
        ArtifactSpec e = b2.addDependency( "e", "1.0" );
        ArtifactSpec g = d1.addDependency( "g", "1.0" );

        ArtifactResolutionResult res = collect( createSet( new Object[] { a.artifact } ) );
        Object[] artifacts = new Object[] { a.artifact, c.artifact, d1.artifact, b2.artifact, e.artifact, g.artifact };
        assertEquals( "Check artifact list", createSet( artifacts ), res.getArtifacts() );
        assertEquals( "Check version", "1.0", getArtifact( "d", res.getArtifacts() ).getVersion() );
        assertEquals( "Check version", "2.0", getArtifact( "b", res.getArtifacts() ).getVersion() );
    }
