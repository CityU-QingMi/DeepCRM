    public void testInclude_CompilePlusRuntime()
    {
        ScopeArtifactFilter filter = new ScopeArtifactFilter( Artifact.SCOPE_COMPILE_PLUS_RUNTIME );

        assertTrue( filter.include( newArtifact( Artifact.SCOPE_COMPILE ) ) );
        assertTrue( filter.include( newArtifact( Artifact.SCOPE_SYSTEM ) ) );
        assertTrue( filter.include( newArtifact( Artifact.SCOPE_PROVIDED ) ) );
        assertTrue( filter.include( newArtifact( Artifact.SCOPE_RUNTIME ) ) );
        assertFalse( filter.include( newArtifact( Artifact.SCOPE_TEST ) ) );
    }
