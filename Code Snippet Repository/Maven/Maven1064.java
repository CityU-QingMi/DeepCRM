    MavenExecutionPlan calculateExecutionPlan( MavenSession session, String... tasks )
        throws Exception
    {
        List<TaskSegment> taskSegments =
            lifeCycleTaskSegmentCalculator.calculateTaskSegments( session, Arrays.asList( tasks ) );

        TaskSegment mergedSegment = new TaskSegment( false );

        for ( TaskSegment taskSegment : taskSegments )
        {
            mergedSegment.getTasks().addAll( taskSegment.getTasks() );
        }

        return lifeCycleExecutionPlanCalculator.calculateExecutionPlan( session, session.getCurrentProject(),
                                                                        mergedSegment.getTasks() );
    }
