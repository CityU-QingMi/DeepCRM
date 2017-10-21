    private void checkScopeUpdateTransitively( String farthestScope, String nearestScope, String expectedScope )
        throws ArtifactResolutionException, InvalidVersionSpecificationException
    {
        ArtifactSpec a = createArtifactSpec( "a", "1.0" );
        ArtifactSpec b = createArtifactSpec( "b", "1.0", nearestScope );
        ArtifactSpec c = createArtifactSpec( "c", "1.0" );
        a.addDependency( c );
        ArtifactSpec dNearest = createArtifactSpec( "d", "2.0" );
        b.addDependency( dNearest );
        ArtifactSpec dFarthest = createArtifactSpec( "d", "3.0", farthestScope );
        c.addDependency( dFarthest );

        /* system and provided dependencies are not transitive */
        if ( !Artifact.SCOPE_SYSTEM.equals( nearestScope ) && !Artifact.SCOPE_PROVIDED.equals( nearestScope ) )
        {
            checkScopeUpdate( a, b, expectedScope, "2.0" );
        }
    }
