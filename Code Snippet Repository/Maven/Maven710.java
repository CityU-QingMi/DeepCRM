    private Set<String> getReactorProjectKeys( Collection<MavenProject> projects )
    {
        Set<String> projectKeys = new HashSet<>( projects.size() * 2 );
        for ( MavenProject project : projects )
        {
            String key = ArtifactUtils.key( project.getGroupId(), project.getArtifactId(), project.getVersion() );
            projectKeys.add( key );
        }
        return projectKeys;
    }
