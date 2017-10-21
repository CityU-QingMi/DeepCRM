    public void testInclude_RuntimePlusSystem()
    {
        ScopeArtifactFilter filter = new ScopeArtifactFilter( Artifact.SCOPE_RUNTIME_PLUS_SYSTEM );

        assertTrue( filter.include( newArtifact( Artifact.SCOPE_COMPILE ) ) );
        assertTrue( filter.include( newArtifact( Artifact.SCOPE_SYSTEM ) ) );
        assertFalse( filter.include( newArtifact( Artifact.SCOPE_PROVIDED ) ) );
        assertTrue( filter.include( newArtifact( Artifact.SCOPE_RUNTIME ) ) );
        assertFalse( filter.include( newArtifact( Artifact.SCOPE_TEST ) ) );
    }
