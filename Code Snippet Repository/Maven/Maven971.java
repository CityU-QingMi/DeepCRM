    private static Artifact createDependencyArtifact( ArtifactFactory factory, Dependency dependency,
                                                      String inheritedScope, ArtifactFilter inheritedFilter )
        throws InvalidVersionSpecificationException
    {
        String effectiveScope = getEffectiveScope( dependency.getScope(), inheritedScope );

        if ( effectiveScope == null )
        {
            return null;
        }

        VersionRange versionRange = VersionRange.createFromVersionSpec( dependency.getVersion() );

        Artifact dependencyArtifact =
            factory.createDependencyArtifact( dependency.getGroupId(), dependency.getArtifactId(), versionRange,
                                              dependency.getType(), dependency.getClassifier(), effectiveScope,
                                              dependency.isOptional() );

        ArtifactFilter dependencyFilter = inheritedFilter;

        if ( dependencyFilter != null && !dependencyFilter.include( dependencyArtifact ) )
        {
            return null;
        }

        if ( Artifact.SCOPE_SYSTEM.equals( effectiveScope ) )
        {
            dependencyArtifact.setFile( new File( dependency.getSystemPath() ) );
        }

        dependencyArtifact.setDependencyFilter( createDependencyFilter( dependency, dependencyFilter ) );

        return dependencyArtifact;
    }
