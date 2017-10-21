    public void testOrderOfPluginExecutionsWithPluginManagement()
        throws Exception
    {
        PomTestWrapper pom = buildPom( "plugin-exec-order/w-plugin-mgmt" );
        assertEquals( 5, ( (List<?>) pom.getValue( "build/plugins[1]/executions" ) ).size() );
        assertEquals( "b", pom.getValue( "build/plugins[1]/executions[1]/id" ) );
        assertEquals( "a", pom.getValue( "build/plugins[1]/executions[2]/id" ) );
        assertEquals( "d", pom.getValue( "build/plugins[1]/executions[3]/id" ) );
        assertEquals( "c", pom.getValue( "build/plugins[1]/executions[4]/id" ) );
        assertEquals( "e", pom.getValue( "build/plugins[1]/executions[5]/id" ) );
    }
