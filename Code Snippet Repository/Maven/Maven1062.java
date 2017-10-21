    public void testLifecycleQueryingUsingADefaultLifecyclePhase()
        throws Exception
    {
        File pom = getProject( "project-with-additional-lifecycle-elements" );
        MavenSession session = createMavenSession( pom );
        assertEquals( "project-with-additional-lifecycle-elements", session.getCurrentProject().getArtifactId() );
        assertEquals( "1.0", session.getCurrentProject().getVersion() );
        List<MojoExecution> executionPlan = getExecutions( calculateExecutionPlan( session, "package" ) );

        //[01] resources:resources
        //[02] compiler:compile
        //[03] it:generate-metadata
        //[04] resources:testResources
        //[05] compiler:testCompile
        //[06] plexus-component-metadata:generate-test-metadata
        //[07] surefire:test
        //[08] jar:jar
        //
        assertEquals( 8, executionPlan.size() );

        assertEquals( "resources:resources", executionPlan.get( 0 ).getMojoDescriptor().getFullGoalName() );
        assertEquals( "compiler:compile", executionPlan.get( 1 ).getMojoDescriptor().getFullGoalName() );
        assertEquals( "it:generate-metadata", executionPlan.get( 2 ).getMojoDescriptor().getFullGoalName() );
        assertEquals( "resources:testResources", executionPlan.get( 3 ).getMojoDescriptor().getFullGoalName() );
        assertEquals( "compiler:testCompile", executionPlan.get( 4 ).getMojoDescriptor().getFullGoalName() );
        assertEquals( "it:generate-test-metadata", executionPlan.get( 5 ).getMojoDescriptor().getFullGoalName() );
        assertEquals( "surefire:test", executionPlan.get( 6 ).getMojoDescriptor().getFullGoalName() );
        assertEquals( "jar:jar", executionPlan.get( 7 ).getMojoDescriptor().getFullGoalName() );
    }
