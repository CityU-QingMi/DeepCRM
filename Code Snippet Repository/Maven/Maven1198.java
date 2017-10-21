    public void testPluginExecutionInheritanceWhenChildDoesNotDeclarePlugin()
        throws Exception
    {
        PomTestWrapper pom = buildPom( "plugin-exec-inheritance/wo-merge" );
        @SuppressWarnings( "unchecked" )
        List<PluginExecution> executions =
            (List<PluginExecution>) pom.getValue( "build/pluginsAsMap[@name='org.apache.maven.its.plugins:maven-it-plugin-log-file']/executions" );
        assertEquals( 1, executions.size() );
        assertEquals( "inherited-execution", executions.get( 0 ).getId() );
    }
