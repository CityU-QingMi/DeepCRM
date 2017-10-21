    public void testOrderOfGoalsFromPluginExecutionWithPluginManagement()
        throws Exception
    {
        PomTestWrapper pom = buildPom( "plugin-exec-goals-order/w-plugin-mgmt" );
        assertEquals( 5, ( (List<?>) pom.getValue( "build/plugins[1]/executions[1]/goals" ) ).size() );
        assertEquals( "b", pom.getValue( "build/plugins[1]/executions[1]/goals[1]" ) );
        assertEquals( "a", pom.getValue( "build/plugins[1]/executions[1]/goals[2]" ) );
        assertEquals( "d", pom.getValue( "build/plugins[1]/executions[1]/goals[3]" ) );
        assertEquals( "c", pom.getValue( "build/plugins[1]/executions[1]/goals[4]" ) );
        assertEquals( "e", pom.getValue( "build/plugins[1]/executions[1]/goals[5]" ) );
    }
