    public void testErroneousJoiningOfDifferentPluginsWithEqualExecutionIds()
        throws Exception
    {
        PomTestWrapper pom = buildPom( "equal-plugin-exec-ids" );
        assertEquals( "maven-it-plugin-a", pom.getValue( "build/plugins[1]/artifactId" ) );
        assertEquals( 1, ( (List<?>) pom.getValue( "build/plugins[1]/executions" ) ).size() );
        assertEquals( "maven-it-plugin-b", pom.getValue( "build/plugins[2]/artifactId" ) );
        assertEquals( 1, ( (List<?>) pom.getValue( "build/plugins[1]/executions" ) ).size() );
        assertEquals( "maven-it-plugin-a", pom.getValue( "reporting/plugins[1]/artifactId" ) );
        assertEquals( 1, ( (List<?>) pom.getValue( "reporting/plugins[1]/reportSets" ) ).size() );
        assertEquals( "maven-it-plugin-b", pom.getValue( "reporting/plugins[2]/artifactId" ) );
        assertEquals( 1, ( (List<?>) pom.getValue( "reporting/plugins[1]/reportSets" ) ).size() );
    }
