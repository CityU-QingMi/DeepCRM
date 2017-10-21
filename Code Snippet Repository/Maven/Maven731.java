    public void blackList( MavenProject project )
    {
        if ( blackListedProjects.add( BuilderCommon.getKey( project ) ) && projectDependencyGraph != null )
        {
            for ( MavenProject downstreamProject : projectDependencyGraph.getDownstreamProjects( project, true ) )
            {
                blackListedProjects.add( BuilderCommon.getKey( downstreamProject ) );
            }
        }
    }
