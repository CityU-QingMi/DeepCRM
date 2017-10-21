    public void testResolveBuildPlan()
        throws Exception
    {
        MavenSession original = ProjectDependencyGraphStub.getMavenSession();

        final TaskSegment taskSegment1 = new TaskSegment( false );
        final MavenSession session1 = original.clone();
        session1.setCurrentProject( ProjectDependencyGraphStub.A );

        final BuilderCommon builderCommon = getBuilderCommon();
        final MavenExecutionPlan plan =
            builderCommon.resolveBuildPlan( session1, ProjectDependencyGraphStub.A, taskSegment1,
                                            new HashSet<Artifact>() );
        assertEquals( LifecycleExecutionPlanCalculatorStub.getProjectAExceutionPlan().size(), plan.size() );

    }
