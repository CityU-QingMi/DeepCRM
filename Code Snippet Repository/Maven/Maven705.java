    public void debugProjectPlan( MavenProject currentProject, MavenExecutionPlan executionPlan )
    {
        if ( !logger.isDebugEnabled() )
        {
            return;
        }

        logger.debug( "=== PROJECT BUILD PLAN ================================================" );
        logger.debug( "Project:       " + BuilderCommon.getKey( currentProject ) );

        debugDependencyRequirements( executionPlan.getMojoExecutions() );

        logger.debug( "Repositories (dependencies): " + currentProject.getRemoteProjectRepositories() );
        logger.debug( "Repositories (plugins)     : " + currentProject.getRemotePluginRepositories() );

        for ( ExecutionPlanItem mojoExecution : executionPlan )
        {
            debugMojoExecution( mojoExecution.getMojoExecution() );
        }

        logger.debug( "=======================================================================" );
    }
