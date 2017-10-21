    public void testCalculationOfBuildPlanTasksOfTheCleanLifecycleAndTheInstallLifecycle()
        throws Exception
    {
        File pom = getProject( "project-with-additional-lifecycle-elements" );
        MavenSession session = createMavenSession( pom );
        assertEquals( "project-with-additional-lifecycle-elements", session.getCurrentProject().getArtifactId() );
        assertEquals( "1.0", session.getCurrentProject().getVersion() );
        List<MojoExecution> executionPlan = getExecutions( calculateExecutionPlan( session, "clean", "install" ) );

        //[01] clean:clean
        //[02] resources:resources
        //[03] compiler:compile
        //[04] it:generate-metadata
        //[05] resources:testResources
        //[06] compiler:testCompile
        //[07] it:generate-test-metadata
        //[08] surefire:test
        //[09] jar:jar
        //[10] install:install
        //
        assertEquals( 10, executionPlan.size() );

        assertEquals( "clean:clean", executionPlan.get( 0 ).getMojoDescriptor().getFullGoalName() );
        assertEquals( "resources:resources", executionPlan.get( 1 ).getMojoDescriptor().getFullGoalName() );
        assertEquals( "compiler:compile", executionPlan.get( 2 ).getMojoDescriptor().getFullGoalName() );
        assertEquals( "it:generate-metadata", executionPlan.get( 3 ).getMojoDescriptor().getFullGoalName() );
        assertEquals( "resources:testResources", executionPlan.get( 4 ).getMojoDescriptor().getFullGoalName() );
        assertEquals( "compiler:testCompile", executionPlan.get( 5 ).getMojoDescriptor().getFullGoalName() );
        assertEquals( "it:generate-test-metadata", executionPlan.get( 6 ).getMojoDescriptor().getFullGoalName() );
        assertEquals( "surefire:test", executionPlan.get( 7 ).getMojoDescriptor().getFullGoalName() );
        assertEquals( "jar:jar", executionPlan.get( 8 ).getMojoDescriptor().getFullGoalName() );
        assertEquals( "install:install", executionPlan.get( 9 ).getMojoDescriptor().getFullGoalName() );
    }
