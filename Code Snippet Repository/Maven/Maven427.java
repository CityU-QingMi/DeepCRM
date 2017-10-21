    public void testCollectChangesVersionOfOriginatingArtifactIfInDependencyManagementHasDifferentVersion()
        throws ArtifactResolutionException, InvalidVersionSpecificationException
    {
        ArtifactSpec a = createArtifactSpec( "a", "1.0" );

        Artifact artifact = projectArtifact.artifact;
        Artifact managedVersion = createArtifactSpec( artifact.getArtifactId(), "2.0" ).artifact;

        ArtifactResolutionResult result = collect( a, managedVersion );

        assertEquals( "collect has modified version in originating artifact", "1.0", artifact.getVersion() );

        Artifact resolvedArtifact = result.getArtifacts().iterator().next();

        assertEquals( "Resolved version don't match original artifact version", "1.0", resolvedArtifact.getVersion() );
    }
