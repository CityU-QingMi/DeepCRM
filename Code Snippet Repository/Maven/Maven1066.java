    public void testFindLastInPhase()
        throws Exception
    {
        MavenExecutionPlan plan = LifecycleExecutionPlanCalculatorStub.getProjectAExceutionPlan();

        ExecutionPlanItem expected = plan.findLastInPhase( "package" );
        ExecutionPlanItem beerPhase = plan.findLastInPhase( "BEER" );  // Beer comes straight after package in stub
        assertEquals( expected, beerPhase );
        assertNotNull( expected );
    }
