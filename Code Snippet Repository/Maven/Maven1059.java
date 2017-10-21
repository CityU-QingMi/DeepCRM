    public void testCalculationOfBuildPlanWithIndividualTaskOfTheCleanLifecycle()
        throws Exception
    {
        // We are doing something like "mvn clean:clean" where no version is specified but this
        // project we are working on has the version specified in the POM so the version should come from there.
        File pom = getProject( "project-basic" );
        MavenSession session = createMavenSession( pom );
        assertEquals( "project-basic", session.getCurrentProject().getArtifactId() );
        assertEquals( "1.0", session.getCurrentProject().getVersion() );
        List<MojoExecution> executionPlan = getExecutions( calculateExecutionPlan( session, "clean" ) );
        assertEquals( 1, executionPlan.size() );
        MojoExecution mojoExecution = executionPlan.get( 0 );
        assertNotNull( mojoExecution );
        assertEquals( "org.apache.maven.plugins",
                      mojoExecution.getMojoDescriptor().getPluginDescriptor().getGroupId() );
        assertEquals( "maven-clean-plugin", mojoExecution.getMojoDescriptor().getPluginDescriptor().getArtifactId() );
        assertEquals( "0.1", mojoExecution.getMojoDescriptor().getPluginDescriptor().getVersion() );
    }
