    public void testUnboundedRangeAboveLastRelease()
        throws ArtifactResolutionException, InvalidVersionSpecificationException
    {
        ArtifactSpec a = createArtifactSpec( "a", "1.0" );
        createArtifactSpec( "c", "2.0" );
        a.addDependency( "c", "[10.0,)" );

        ArtifactResolutionResult res = collect( a );

        assertTrue( res.hasVersionRangeViolations() );
    }
