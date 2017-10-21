    private Result<ProjectDependencyGraph> reactorDependencyGraph( MavenSession session, List<MavenProject> projects )
        throws CycleDetectedException, DuplicateProjectException, MavenExecutionException
    {
        ProjectDependencyGraph projectDependencyGraph = new DefaultProjectDependencyGraph( projects );
        List<MavenProject> activeProjects = projectDependencyGraph.getSortedProjects();
        activeProjects = trimSelectedProjects( activeProjects, projectDependencyGraph, session.getRequest() );
        activeProjects = trimExcludedProjects( activeProjects, session.getRequest() );
        activeProjects = trimResumedProjects( activeProjects, session.getRequest() );

        if ( activeProjects.size() != projectDependencyGraph.getSortedProjects().size() )
        {
            projectDependencyGraph = new FilteredProjectDependencyGraph( projectDependencyGraph, activeProjects );
        }

        return Result.success( projectDependencyGraph );
    }
