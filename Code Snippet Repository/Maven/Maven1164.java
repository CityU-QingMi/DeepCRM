    public void testOrderOfMergedPluginExecutionsWithPluginManagement()
        throws Exception
    {
        PomTestWrapper pom = buildPom( "merged-plugin-exec-order/w-plugin-mgmt/sub" );
        assertEquals( 5, ( (List<?>) pom.getValue( "build/plugins[1]/executions" ) ).size() );
        assertEquals( "parent-1", pom.getValue( "build/plugins[1]/executions[1]/goals[1]" ) );
        assertEquals( "parent-2", pom.getValue( "build/plugins[1]/executions[2]/goals[1]" ) );
        assertEquals( "child-default", pom.getValue( "build/plugins[1]/executions[3]/goals[1]" ) );
        assertEquals( "child-1", pom.getValue( "build/plugins[1]/executions[4]/goals[1]" ) );
        assertEquals( "child-2", pom.getValue( "build/plugins[1]/executions[5]/goals[1]" ) );
    }
