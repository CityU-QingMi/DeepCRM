    public void testSetupMojoExecution()
        throws Exception
    {
        File pom = getProject( "mojo-configuration" );

        MavenSession session = createMavenSession( pom );

        LifecycleTask task = new LifecycleTask( "generate-sources" );
        MavenExecutionPlan executionPlan =
            lifeCycleExecutionPlanCalculator.calculateExecutionPlan( session, session.getCurrentProject(),
                                                                     Arrays.asList( (Object) task ), false );

        MojoExecution execution = executionPlan.getMojoExecutions().get(0);
        assertEquals(execution.toString(), "maven-it-plugin", execution.getArtifactId());
        assertNull(execution.getConfiguration());

        lifeCycleExecutionPlanCalculator.setupMojoExecution( session, session.getCurrentProject(), execution );
        assertNotNull(execution.getConfiguration());
        assertEquals("1.0", execution.getConfiguration().getChild( "version" ).getAttribute( "default-value" ));
    }
