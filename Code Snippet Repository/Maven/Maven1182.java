    public void testDependencyOrderWithPluginManagement()
        throws Exception
    {
        PomTestWrapper pom = buildPom( "dependency-order/w-plugin-mgmt" );
        assertEquals( 4, ( (List<?>) pom.getValue( "dependencies" ) ).size() );
        assertEquals( "a", pom.getValue( "dependencies[1]/artifactId" ) );
        assertEquals( "c", pom.getValue( "dependencies[2]/artifactId" ) );
        assertEquals( "b", pom.getValue( "dependencies[3]/artifactId" ) );
        assertEquals( "d", pom.getValue( "dependencies[4]/artifactId" ) );
    }
