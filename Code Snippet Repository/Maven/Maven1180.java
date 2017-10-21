    private void testAppendOfInheritedPluginConfiguration( String test )
        throws Exception
    {
        PomTestWrapper pom = buildPom( "plugin-config-append/" + test + "/subproject" );
        String prefix = "build/plugins[1]/configuration/";
        assertEquals( "PARENT-1", pom.getValue( prefix + "stringParams/stringParam[1]" ) );
        assertEquals( "PARENT-3", pom.getValue( prefix + "stringParams/stringParam[2]" ) );
        assertEquals( "PARENT-2", pom.getValue( prefix + "stringParams/stringParam[3]" ) );
        assertEquals( "PARENT-4", pom.getValue( prefix + "stringParams/stringParam[4]" ) );
        assertEquals( "CHILD-1", pom.getValue( prefix + "stringParams/stringParam[5]" ) );
        assertEquals( "CHILD-3", pom.getValue( prefix + "stringParams/stringParam[6]" ) );
        assertEquals( "CHILD-2", pom.getValue( prefix + "stringParams/stringParam[7]" ) );
        assertEquals( "CHILD-4", pom.getValue( prefix + "stringParams/stringParam[8]" ) );
        assertEquals( null, pom.getValue( prefix + "stringParams/stringParam[9]" ) );
        assertEquals( "PARENT-1", pom.getValue( prefix + "listParam/listParam[1]" ) );
        assertEquals( "PARENT-3", pom.getValue( prefix + "listParam/listParam[2]" ) );
        assertEquals( "PARENT-2", pom.getValue( prefix + "listParam/listParam[3]" ) );
        assertEquals( "PARENT-4", pom.getValue( prefix + "listParam/listParam[4]" ) );
        assertEquals( "CHILD-1", pom.getValue( prefix + "listParam/listParam[5]" ) );
        assertEquals( "CHILD-3", pom.getValue( prefix + "listParam/listParam[6]" ) );
        assertEquals( "CHILD-2", pom.getValue( prefix + "listParam/listParam[7]" ) );
        assertEquals( "CHILD-4", pom.getValue( prefix + "listParam/listParam[8]" ) );
        assertEquals( null, pom.getValue( prefix + "listParam/listParam[9]" ) );
    }
