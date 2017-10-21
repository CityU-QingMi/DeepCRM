    public void testInclude_Runtime()
    {
        ScopeArtifactFilter filter = new ScopeArtifactFilter( Artifact.SCOPE_RUNTIME );

        assertTrue( filter.include( newArtifact( Artifact.SCOPE_COMPILE ) ) );
        assertFalse( filter.include( newArtifact( Artifact.SCOPE_SYSTEM ) ) );
        assertFalse( filter.include( newArtifact( Artifact.SCOPE_PROVIDED ) ) );
        assertTrue( filter.include( newArtifact( Artifact.SCOPE_RUNTIME ) ) );
        assertFalse( filter.include( newArtifact( Artifact.SCOPE_TEST ) ) );
    }
