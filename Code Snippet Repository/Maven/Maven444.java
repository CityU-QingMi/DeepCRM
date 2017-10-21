    public void testOverConstrainedVersionException()
        throws ArtifactResolutionException, InvalidVersionSpecificationException
    {
        ArtifactSpec a = createArtifactSpec( "a", "1.0" );
        a.addDependency( "b", "[1.0, 2.0)" );
        a.addDependency( "c", "[3.3.0,4.0.0)" );

        ArtifactSpec b = createArtifactSpec( "b", "1.0.0" );
        b.addDependency( "c", "3.3.0-v3346" );

        ArtifactSpec c = createArtifactSpec( "c", "3.2.1-v3235e" );

        try
        {
            ArtifactResolutionResult res = collect( createSet( new Object[] { a.artifact } ) );
        }
        catch ( OverConstrainedVersionException e )
        {
            assertTrue( "Versions unordered", e.getMessage().contains( "[3.2.1-v3235e, 3.3.0-v3346]" ) );
            assertTrue( "DependencyTrail unresolved", e.getMessage().contains( "Path to dependency:" ) );
        }
    }
