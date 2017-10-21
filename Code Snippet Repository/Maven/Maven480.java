    private Set<String> getIgnorableArtifacts( Iterable<Artifact> artifactIterable )
    {
        Set<String> projectIds = new HashSet<>();

        for ( Artifact artifact : artifactIterable )
        {
            String key = ArtifactUtils.key( artifact );
            projectIds.add( key );
        }
        return projectIds;
    }
