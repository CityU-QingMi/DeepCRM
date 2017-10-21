    public void testIncompatibleRanges()
        throws ArtifactResolutionException, InvalidVersionSpecificationException
    {
        ArtifactSpec a = createArtifactSpec( "a", "1.0" );
        ArtifactSpec b = a.addDependency( "b", "1.0" );
        a.addDependency( "c", "[2.4,3.0]" );

        b.addDependency( "c", "[1.0,2.0]" );

        ArtifactResolutionResult res = collect( a );

        assertTrue( res.hasVersionRangeViolations() );
    }
