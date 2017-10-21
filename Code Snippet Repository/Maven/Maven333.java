    public void testInclude_Test()
    {
        ScopeArtifactFilter filter = new ScopeArtifactFilter( Artifact.SCOPE_TEST );

        assertTrue( filter.include( newArtifact( Artifact.SCOPE_COMPILE ) ) );
        assertTrue( filter.include( newArtifact( Artifact.SCOPE_SYSTEM ) ) );
        assertTrue( filter.include( newArtifact( Artifact.SCOPE_PROVIDED ) ) );
        assertTrue( filter.include( newArtifact( Artifact.SCOPE_RUNTIME ) ) );
        assertTrue( filter.include( newArtifact( Artifact.SCOPE_TEST ) ) );
    }
