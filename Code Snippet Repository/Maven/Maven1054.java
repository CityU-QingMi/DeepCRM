    protected void setUp()
        throws Exception
    {
        super.setUp();
        defaultLifeCycles = lookup( DefaultLifecycles.class );
        mojoExecutor = lookup( MojoExecutor.class );
        lifeCycleBuilder = lookup( LifecycleModuleBuilder.class );
        lifeCycleDependencyResolver = lookup( LifecycleDependencyResolver.class );
        lifeCycleExecutionPlanCalculator = lookup( LifecycleExecutionPlanCalculator.class );
        lifeCyclePluginAnalyzer = lookup( LifeCyclePluginAnalyzer.class );
        lifeCycleTaskSegmentCalculator = lookup( LifecycleTaskSegmentCalculator.class );
        lookup( ExceptionHandler.class );
    }
