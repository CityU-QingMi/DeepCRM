    public void testInclude_Compile()
    {
        ScopeArtifactFilter filter = new ScopeArtifactFilter( Artifact.SCOPE_COMPILE );

        assertTrue( filter.include( newArtifact( Artifact.SCOPE_COMPILE ) ) );
        assertTrue( filter.include( newArtifact( Artifact.SCOPE_SYSTEM ) ) );
        assertTrue( filter.include( newArtifact( Artifact.SCOPE_PROVIDED ) ) );
        assertFalse( filter.include( newArtifact( Artifact.SCOPE_RUNTIME ) ) );
        assertFalse( filter.include( newArtifact( Artifact.SCOPE_TEST ) ) );
    }
