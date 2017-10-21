    private Result<ProjectDependencyGraph> sessionDependencyGraph( final MavenSession session )
        throws CycleDetectedException, DuplicateProjectException
    {
        Result<ProjectDependencyGraph> result = null;

        if ( session.getProjectDependencyGraph() != null || session.getProjects() != null )
        {
            final ProjectDependencyGraph graph =
                new DefaultProjectDependencyGraph( session.getAllProjects(), session.getProjects() );

            result = Result.success( graph );
        }

        return result;
    }
