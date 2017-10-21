    public MavenExecutionPlan resolveBuildPlan( MavenSession session, MavenProject project, TaskSegment taskSegment,
                                                Set<Artifact> projectArtifacts )
        throws PluginNotFoundException, PluginResolutionException, LifecyclePhaseNotFoundException,
        PluginDescriptorParsingException, MojoNotFoundException, InvalidPluginDescriptorException,
        NoPluginFoundForPrefixException, LifecycleNotFoundException, PluginVersionResolutionException,
        LifecycleExecutionException
    {
        MavenExecutionPlan executionPlan =
            lifeCycleExecutionPlanCalculator.calculateExecutionPlan( session, project, taskSegment.getTasks() );

        lifecycleDebugLogger.debugProjectPlan( project, executionPlan );

        if ( session.getRequest().getDegreeOfConcurrency() > 1 )
        {
            final Set<Plugin> unsafePlugins = executionPlan.getNonThreadSafePlugins();
            if ( !unsafePlugins.isEmpty() )
            {
                logger.warn( "*****************************************************************" );
                logger.warn( "* Your build is requesting parallel execution, but project      *" );
                logger.warn( "* contains the following plugin(s) that have goals not marked   *" );
                logger.warn( "* as @threadSafe to support parallel building.                  *" );
                logger.warn( "* While this /may/ work fine, please look for plugin updates    *" );
                logger.warn( "* and/or request plugins be made thread-safe.                   *" );
                logger.warn( "* If reporting an issue, report it against the plugin in        *" );
                logger.warn( "* question, not against maven-core                              *" );
                logger.warn( "*****************************************************************" );
                if ( logger.isDebugEnabled() )
                {
                    final Set<MojoDescriptor> unsafeGoals = executionPlan.getNonThreadSafeMojos();
                    logger.warn( "The following goals are not marked @threadSafe in " + project.getName() + ":" );
                    for ( MojoDescriptor unsafeGoal : unsafeGoals )
                    {
                        logger.warn( unsafeGoal.getId() );
                    }
                }
                else
                {
                    logger.warn( "The following plugins are not marked @threadSafe in " + project.getName() + ":" );
                    for ( Plugin unsafePlugin : unsafePlugins )
                    {
                        logger.warn( unsafePlugin.getId() );
                    }
                    logger.warn( "Enable debug to see more precisely which goals are not marked @threadSafe." );
                }
                logger.warn( "*****************************************************************" );
            }
        }

        return executionPlan;
    }
