    private boolean areAllDependenciesInReactor( Collection<MavenProject> projects,
                                                 Collection<Dependency> dependencies )
    {
        Set<String> projectKeys = getReactorProjectKeys( projects );

        for ( Dependency dependency : dependencies )
        {
            org.eclipse.aether.artifact.Artifact a = dependency.getArtifact();
            String key = ArtifactUtils.key( a.getGroupId(), a.getArtifactId(), a.getVersion() );
            if ( !projectKeys.contains( key ) )
            {
                return false;
            }
        }

        return true;
    }
