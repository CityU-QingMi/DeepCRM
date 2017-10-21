    private void checkScopeUpdateDirect( String farthestScope, String nearestScope, String expectedScope )
        throws ArtifactResolutionException, InvalidVersionSpecificationException
    {
        ArtifactSpec a = createArtifactSpec( "a", "1.0" );
        ArtifactSpec b = createArtifactSpec( "b", "1.0" );
        ArtifactSpec c = createArtifactSpec( "c", "1.0" );
        a.addDependency( c );
        ArtifactSpec dNearest = createArtifactSpec( "d", "2.0", nearestScope );
        b.addDependency( dNearest );
        ArtifactSpec dFarthest = createArtifactSpec( "d", "3.0", farthestScope );
        c.addDependency( dFarthest );

        checkScopeUpdate( a, b, expectedScope, "2.0" );
    }
