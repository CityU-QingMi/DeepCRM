    public void testOrderOfMergedPluginDependenciesWithPluginManagement()
        throws Exception
    {
        PomTestWrapper pom = buildPom( "merged-plugin-class-path-order/w-plugin-mgmt/sub" );
        assertEquals( 5, ( (List<?>) pom.getValue( "build/plugins[1]/dependencies" ) ).size() );
        assertEquals( "c", pom.getValue( "build/plugins[1]/dependencies[1]/artifactId" ) );
        assertEquals( "1", pom.getValue( "build/plugins[1]/dependencies[1]/version" ) );
        assertEquals( "a", pom.getValue( "build/plugins[1]/dependencies[2]/artifactId" ) );
        assertEquals( "2", pom.getValue( "build/plugins[1]/dependencies[2]/version" ) );
        assertEquals( "b", pom.getValue( "build/plugins[1]/dependencies[3]/artifactId" ) );
        assertEquals( "1", pom.getValue( "build/plugins[1]/dependencies[3]/version" ) );
        assertEquals( "e", pom.getValue( "build/plugins[1]/dependencies[4]/artifactId" ) );
        assertEquals( "1", pom.getValue( "build/plugins[1]/dependencies[4]/version" ) );
        assertEquals( "d", pom.getValue( "build/plugins[1]/dependencies[5]/artifactId" ) );
        assertEquals( "1", pom.getValue( "build/plugins[1]/dependencies[5]/version" ) );
    }
