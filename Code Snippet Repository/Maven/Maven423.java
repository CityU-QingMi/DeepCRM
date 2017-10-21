    public void testUnboundedRangeBelowLastRelease()
        throws ArtifactResolutionException, InvalidVersionSpecificationException
    {
        ArtifactSpec a = createArtifactSpec( "a", "1.0" );
        createArtifactSpec( "c", "1.5" );
        ArtifactSpec c = createArtifactSpec( "c", "2.0" );
        createArtifactSpec( "c", "1.1" );
        a.addDependency( "c", "[1.0,)" );

        ArtifactResolutionResult res = collect( a );

        assertEquals( "Check artifact list", createSet( new Object[] { a.artifact, c.artifact } ), res.getArtifacts() );
        assertEquals( "Check version", "2.0", getArtifact( "c", res.getArtifacts() ).getVersion() );
    }
