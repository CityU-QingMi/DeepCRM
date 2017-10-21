    public void testExecutionConfiguration()
        throws Exception
    {
        PomTestWrapper pom = buildPom( "execution-configuration" );
        assertEquals( 2, ( (List<?>) pom.getValue( "build/plugins[1]/executions" ) ).size() );
        assertEquals( "src/main/mdo/nexus.xml",
                      ( pom.getValue( "build/plugins[1]/executions[1]/configuration[1]/model" ) ) );
        assertEquals( "src/main/mdo/security.xml",
                      ( pom.getValue( "build/plugins[1]/executions[2]/configuration[1]/model" ) ) );
    }
