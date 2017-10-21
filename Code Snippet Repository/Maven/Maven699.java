    public List<TaskSegment> calculateTaskSegments( MavenSession session, List<String> tasks )
        throws PluginNotFoundException, PluginResolutionException, PluginDescriptorParsingException,
        MojoNotFoundException, NoPluginFoundForPrefixException, InvalidPluginDescriptorException,
        PluginVersionResolutionException
    {
        List<TaskSegment> taskSegments = new ArrayList<>( tasks.size() );

        TaskSegment currentSegment = null;

        for ( String task : tasks )
        {
            if ( isGoalSpecification( task ) )
            {
                // "pluginPrefix:goal" or "groupId:artifactId[:version]:goal"

                lifecyclePluginResolver.resolveMissingPluginVersions( session.getTopLevelProject(), session );

                MojoDescriptor mojoDescriptor =
                    mojoDescriptorCreator.getMojoDescriptor( task, session, session.getTopLevelProject() );

                boolean aggregating = mojoDescriptor.isAggregator() || !mojoDescriptor.isProjectRequired();

                if ( currentSegment == null || currentSegment.isAggregating() != aggregating )
                {
                    currentSegment = new TaskSegment( aggregating );
                    taskSegments.add( currentSegment );
                }

                currentSegment.getTasks().add( new GoalTask( task ) );
            }
            else
            {
                // lifecycle phase

                if ( currentSegment == null || currentSegment.isAggregating() )
                {
                    currentSegment = new TaskSegment( false );
                    taskSegments.add( currentSegment );
                }

                currentSegment.getTasks().add( new LifecycleTask( task ) );
            }
        }

        return taskSegments;
    }
