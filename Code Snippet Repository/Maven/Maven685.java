    @Override
    public MavenExecutionPlan calculateExecutionPlan( MavenSession session, MavenProject project, List<Object> tasks,
                                                      boolean setup )
        throws PluginNotFoundException, PluginResolutionException, LifecyclePhaseNotFoundException,
        PluginDescriptorParsingException, MojoNotFoundException, InvalidPluginDescriptorException,
        NoPluginFoundForPrefixException, LifecycleNotFoundException, PluginVersionResolutionException
    {
        lifecyclePluginResolver.resolveMissingPluginVersions( project, session );

        final List<MojoExecution> executions = calculateMojoExecutions( session, project, tasks );

        if ( setup )
        {
            setupMojoExecutions( session, project, executions );
        }

        final List<ExecutionPlanItem> planItem = ExecutionPlanItem.createExecutionPlanItems( project, executions );

        return new MavenExecutionPlan( planItem, defaultLifeCycles );
    }
