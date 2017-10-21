    public void testDefaultPluginsExecutionContributedByPackagingExecuteBeforeUserDefinedExecutions()
        throws Exception
    {
        PomTestWrapper pom = buildPom( "plugin-exec-order-and-default-exec" );
        @SuppressWarnings( "unchecked" )
        List<PluginExecution> executions =
            (List<PluginExecution>) pom.getValue( "build/plugins[@artifactId='maven-resources-plugin']/executions" );
        assertNotNull( executions );
        assertEquals( 4, executions.size() );
        assertEquals( "default-resources", executions.get( 0 ).getId() );
        assertEquals( "default-testResources", executions.get( 1 ).getId() );
        assertEquals( "test-1", executions.get( 2 ).getId() );
        assertEquals( "test-2", executions.get( 3 ).getId() );
    }
