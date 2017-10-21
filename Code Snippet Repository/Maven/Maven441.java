    public void disabledtestOptionalNotTransitiveButVersionIsInfluential()
        throws ArtifactResolutionException, InvalidVersionSpecificationException
    {
        ArtifactSpec a = createArtifactSpec( "a", "1.0" );
        ArtifactSpec b = createArtifactSpec( "b", "1.0" );
        b.addDependency( "c", "3.0", true );
        ArtifactSpec d = a.addDependency( "d", "1.0" );
        ArtifactSpec e = d.addDependency( "e", "1.0" );
        e.addDependency( "c", "2.0" );

        ArtifactSpec c = createArtifactSpec( "c", "3.0" );

        ArtifactResolutionResult res = collect( createSet( new Object[] { a.artifact, b.artifact } ) );
        assertEquals( "Check artifact list", createSet( new Object[] { a.artifact, b.artifact, c.artifact, d.artifact,
            e.artifact } ), res.getArtifacts() );
        Artifact artifact = getArtifact( "c", res.getArtifacts() );
        assertEquals( "Check version", "3.0", artifact.getVersion() );
    }
