    private Artifact createDependencyArtifact( Dependency dependency, Artifact owner, Artifact pom )
        throws ArtifactMetadataRetrievalException
    {
        try
        {
            String inheritedScope = ( owner != null ) ? owner.getScope() : null;

            ArtifactFilter inheritedFilter = ( owner != null ) ? owner.getDependencyFilter() : null;

            return createDependencyArtifact( repositorySystem, dependency, inheritedScope, inheritedFilter );
        }
        catch ( InvalidVersionSpecificationException e )
        {
            throw new ArtifactMetadataRetrievalException( "Invalid version for dependency "
                + dependency.getManagementKey() + ": " + e.getMessage(), e, pom );
        }
    }
