    private Result<? extends ProjectDependencyGraph> buildGraph( MavenSession session, MavenExecutionResult result )
    {
        Result<? extends ProjectDependencyGraph> graphResult = graphBuilder.build( session );
        for ( ModelProblem problem : graphResult.getProblems() )
        {
            if ( problem.getSeverity() == ModelProblem.Severity.WARNING )
            {
                logger.warn( problem.toString() );
            }
            else
            {
                logger.error( problem.toString() );
            }
        }

        if ( !graphResult.hasErrors() )
        {
            ProjectDependencyGraph projectDependencyGraph = graphResult.get();
            session.setProjects( projectDependencyGraph.getSortedProjects() );
            session.setAllProjects( projectDependencyGraph.getAllProjects() );
            session.setProjectDependencyGraph( projectDependencyGraph );
        }

        return graphResult;
    }
