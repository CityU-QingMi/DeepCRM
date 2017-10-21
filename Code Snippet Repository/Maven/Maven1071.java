    public void testCalculateExecutionPlanWithGoalTasks()
        throws Exception
    {
        MojoDescriptorCreator mojoDescriptorCreator = createMojoDescriptorCreator();
        LifecycleExecutionPlanCalculator lifecycleExecutionPlanCalculator =
            createExecutionPlaceCalculator( mojoDescriptorCreator );

        final GoalTask goalTask1 = new GoalTask( "compiler:compile" );
        final GoalTask goalTask2 = new GoalTask( "surefire:test" );
        final TaskSegment taskSegment1 = new TaskSegment( false, goalTask1, goalTask2 );
        final MavenSession session1 = ProjectDependencyGraphStub.getMavenSession( ProjectDependencyGraphStub.A );

        MavenExecutionPlan executionPlan =
            lifecycleExecutionPlanCalculator.calculateExecutionPlan( session1, ProjectDependencyGraphStub.A,
                                                                     taskSegment1.getTasks() );
        assertEquals( 2, executionPlan.size() );

        final GoalTask goalTask3 = new GoalTask( "surefire:test" );
        final TaskSegment taskSegment2 = new TaskSegment( false, goalTask1, goalTask2, goalTask3 );
        MavenExecutionPlan executionPlan2 =
            lifecycleExecutionPlanCalculator.calculateExecutionPlan( session1, ProjectDependencyGraphStub.A,
                                                                     taskSegment2.getTasks() );
        assertEquals( 3, executionPlan2.size() );
    }
