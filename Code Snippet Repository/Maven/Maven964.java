    @Deprecated
    public static Set<Artifact> createArtifacts( ArtifactFactory artifactFactory, List<Dependency> dependencies,
                                                 String inheritedScope, ArtifactFilter dependencyFilter,
                                                 MavenProject project )
        throws InvalidDependencyVersionException
    {
        Set<Artifact> artifacts = new LinkedHashSet<>();

        for ( Dependency d : dependencies )
        {
            Artifact dependencyArtifact;
            try
            {
                dependencyArtifact = createDependencyArtifact( artifactFactory, d, inheritedScope, dependencyFilter );
            }
            catch ( InvalidVersionSpecificationException e )
            {
                throw new InvalidDependencyVersionException( project.getId(), d, project.getFile(), e );
            }

            if ( dependencyArtifact != null )
            {
                artifacts.add( dependencyArtifact );
            }
        }

        return artifacts;
    }
