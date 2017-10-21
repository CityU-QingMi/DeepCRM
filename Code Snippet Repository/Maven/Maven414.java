    public void testResolveLocalNewestIsLocal()
        throws ArtifactResolutionException, InvalidVersionSpecificationException
    {
        ArtifactSpec a = createArtifactSpec( "a", "1.0" );
        a.addDependency( "b", "2.0" );
        ArtifactSpec b = createArtifactSpec( "b", "3.0" );

        ArtifactResolutionResult res = collect( createSet( new Object[] { a.artifact, b.artifact } ) );
        assertEquals( "Check artifact list", createSet( new Object[] { a.artifact, b.artifact } ), res.getArtifacts() );
        assertEquals( "Check version", "3.0", getArtifact( "b", res.getArtifacts() ).getVersion() );
    }
