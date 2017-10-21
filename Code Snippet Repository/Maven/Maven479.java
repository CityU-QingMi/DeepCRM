    private Set<String> getIgnorableArtifacts( Collection<? extends MavenProject> projects )
    {
        Set<String> projectIds = new HashSet<>( projects.size() * 2 );

        for ( MavenProject p : projects )
        {
            String key = ArtifactUtils.key( p.getGroupId(), p.getArtifactId(), p.getVersion() );
            projectIds.add( key );
        }
        return projectIds;
    }
