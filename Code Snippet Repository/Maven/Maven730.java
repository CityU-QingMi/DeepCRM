    public ProjectIndex( List<MavenProject> projects )
    {
        this.projects = new HashMap<>( projects.size() * 2 );
        this.indices = new HashMap<>( projects.size() * 2 );

        for ( int i = 0; i < projects.size(); i++ )
        {
            MavenProject project = projects.get( i );
            String key = BuilderCommon.getKey( project );

            this.getProjects().put( key, project );
            this.getIndices().put( key, i );
        }
    }
