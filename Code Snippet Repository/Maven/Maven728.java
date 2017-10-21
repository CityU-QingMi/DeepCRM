    public Set<String> getReactorProjectKeys()
    {
        Set<String> projectKeys = new HashSet<>( items.size() * 2 );
        for ( ProjectSegment projectBuild : items )
        {
            MavenProject project = projectBuild.getProject();
            String key = ArtifactUtils.key( project.getGroupId(), project.getArtifactId(), project.getVersion() );
            projectKeys.add( key );
        }
        return projectKeys;
    }
