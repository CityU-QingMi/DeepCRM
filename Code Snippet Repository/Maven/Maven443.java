    public void testSnapshotNotIncluded()
        throws ArtifactResolutionException, InvalidVersionSpecificationException
    {
        ArtifactSpec a = createArtifactSpec( "a", "1.0" );
        a.addDependency( "b", "[1.0,)" );
        createArtifactSpec( "b", "1.0-SNAPSHOT" );

        ArtifactResolutionResult res = collect( a );

        assertTrue( res.hasVersionRangeViolations() );

/**/
/**/
/**/
/**/
/**/
    }
