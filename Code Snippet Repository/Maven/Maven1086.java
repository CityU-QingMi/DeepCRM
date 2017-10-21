    public List<TaskSegment> calculateTaskSegments( MavenSession session, List<String> tasks )
        throws PluginNotFoundException, PluginResolutionException, PluginDescriptorParsingException,
        MojoNotFoundException, NoPluginFoundForPrefixException, InvalidPluginDescriptorException,
        PluginVersionResolutionException
    {
        List<TaskSegment> taskSegments = new ArrayList<>( tasks.size() );

        TaskSegment currentSegment = null;

        for ( String task : tasks )
        {
            if ( aggr.equals( task ) )
            {
                boolean aggregating = true;

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
