    public void testMergeOfInheritedPluginConfiguration()
        throws Exception
    {
        PomTestWrapper pom = buildPom( "plugin-config-merging/child" );

        String prefix = "build/plugins[1]/configuration/";
        assertEquals( "PASSED", pom.getValue( prefix + "propertiesFile" ) );
        assertEquals( "PASSED", pom.getValue( prefix + "parent" ) );
        assertEquals( "PASSED-1", pom.getValue( prefix + "stringParams/stringParam[1]" ) );
        assertEquals( "PASSED-3", pom.getValue( prefix + "stringParams/stringParam[2]" ) );
        assertEquals( "PASSED-2", pom.getValue( prefix + "stringParams/stringParam[3]" ) );
        assertEquals( "PASSED-4", pom.getValue( prefix + "stringParams/stringParam[4]" ) );
        assertEquals( "PASSED-1", pom.getValue( prefix + "listParam/listParam[1]" ) );
        assertEquals( "PASSED-3", pom.getValue( prefix + "listParam/listParam[2]" ) );
        assertEquals( "PASSED-2", pom.getValue( prefix + "listParam/listParam[3]" ) );
        assertEquals( "PASSED-4", pom.getValue( prefix + "listParam/listParam[4]" ) );
    }
