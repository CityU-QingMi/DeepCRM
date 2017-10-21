    protected void setUp()
        throws Exception
    {
        super.setUp();
        lifecycleExecutor = (DefaultLifecycleExecutor) lookup( LifecycleExecutor.class );
        lifeCycleTaskSegmentCalculator =
            (DefaultLifecycleTaskSegmentCalculator) lookup( LifecycleTaskSegmentCalculator.class );
        lifeCycleExecutionPlanCalculator = lookup( LifecycleExecutionPlanCalculator.class );
        mojoDescriptorCreator = lookup( MojoDescriptorCreator.class );
        lookup( ExceptionHandler.class );
    }
