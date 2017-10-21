    public void testObserveExecution() throws Exception {
        PhaseRecorder phaseRecorder = new PhaseRecorder( ProjectDependencyGraphStub.A);
        MavenExecutionPlan plan = LifecycleExecutionPlanCalculatorStub.getProjectAExceutionPlan();
        final List<MojoExecution> executions = plan.getMojoExecutions();

        final MojoExecution mojoExecution1 = executions.get( 0 );
        final MojoExecution mojoExecution2 = executions.get( 1 );
        phaseRecorder.observeExecution( mojoExecution1 );

        assertTrue( ProjectDependencyGraphStub.A.hasLifecyclePhase( mojoExecution1.getLifecyclePhase() ));
        assertFalse( ProjectDependencyGraphStub.A.hasLifecyclePhase( mojoExecution2.getLifecyclePhase() ));

        assertFalse( phaseRecorder.isDifferentPhase( mojoExecution1));
        assertTrue( phaseRecorder.isDifferentPhase( mojoExecution2));

    }
