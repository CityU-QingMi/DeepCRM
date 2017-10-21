    private void checkScopeUpdate( ArtifactSpec a, ArtifactSpec b, String expectedScope, String expectedVersion )
        throws ArtifactResolutionException, InvalidVersionSpecificationException
    {
        ScopeArtifactFilter filter;
        if ( Artifact.SCOPE_PROVIDED.equals( expectedScope ) )
        {
            filter = new ScopeArtifactFilter( Artifact.SCOPE_COMPILE );
        }
        else if ( Artifact.SCOPE_SYSTEM.equals( expectedScope ) )
        {
            filter = new ScopeArtifactFilter( Artifact.SCOPE_COMPILE );
        }
        else
        {
            filter = new ScopeArtifactFilter( expectedScope );
        }

        ArtifactResolutionResult res = collect( createSet( new Object[] { a.artifact, b.artifact } ), filter );
        Artifact artifact = getArtifact( "d", res.getArtifacts() );
        assertNotNull( "MNG-1895 Dependency was not added to resolution", artifact );
        assertEquals( "Check artifactScope", expectedScope, artifact.getScope() );
        assertEquals( "Check version", expectedVersion, artifact.getVersion() );

        ArtifactSpec d = createArtifactSpec( "d", "1.0" );
        res = collect( createSet( new Object[] { a.artifact, b.artifact, d.artifact } ), filter );
        artifact = getArtifact( "d", res.getArtifacts() );
        assertNotNull( "MNG-1895 Dependency was not added to resolution", artifact );
        assertEquals( "Check artifactScope", d.artifact.getScope(), artifact.getScope() );
        assertEquals( "Check version", "1.0", artifact.getVersion() );
    }
