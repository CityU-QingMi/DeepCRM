    public FilteredProjectDependencyGraph( ProjectDependencyGraph projectDependencyGraph,
                                           Collection<? extends MavenProject> whiteList )
    {
        this.projectDependencyGraph =
            Validate.notNull( projectDependencyGraph, "projectDependencyGraph cannot be null" );

        this.whiteList = new IdentityHashMap<MavenProject, Object>();

        for ( MavenProject project : whiteList )
        {
            this.whiteList.put( project, null );
        }
    }
