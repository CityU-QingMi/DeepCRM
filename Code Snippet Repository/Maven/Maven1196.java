    public void testSingleConfigurationInheritance()
        throws Exception
    {
        PomTestWrapper pom = buildPom( "single-configuration-inheritance" );

        assertEquals( 2, ( (List<?>) pom.getValue( "build/plugins[1]/executions[1]/configuration[1]/rules" ) ).size() );
        assertEquals( "2.0.6",
                      pom.getValue( "build/plugins[1]/executions[1]/configuration[1]/rules[1]/requireMavenVersion[1]/version" ) );
        assertEquals( "[1.4,)",
                      pom.getValue( "build/plugins[1]/executions[1]/configuration[1]/rules[1]/requireJavaVersion[1]/version" ) );
    }
