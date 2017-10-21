    @SuppressWarnings( { "" } )
    public MavenExecutionPlan calculateExecutionPlan( MavenSession session, boolean setup, String... tasks )
        throws PluginNotFoundException, PluginResolutionException, PluginDescriptorParsingException,
        MojoNotFoundException, NoPluginFoundForPrefixException, InvalidPluginDescriptorException,
        PluginManagerException, LifecyclePhaseNotFoundException, LifecycleNotFoundException,
        PluginVersionResolutionException
    {
        List<TaskSegment> taskSegments =
            lifecycleTaskSegmentCalculator.calculateTaskSegments( session, Arrays.asList( tasks ) );

        TaskSegment mergedSegment = new TaskSegment( false );

        for ( TaskSegment taskSegment : taskSegments )
        {
            mergedSegment.getTasks().addAll( taskSegment.getTasks() );
        }

        return lifecycleExecutionPlanCalculator.calculateExecutionPlan( session, session.getCurrentProject(),
                                                                        mergedSegment.getTasks(), setup );
    }
