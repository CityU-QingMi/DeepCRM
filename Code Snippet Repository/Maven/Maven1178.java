    public void testOrderOfPluginExecutionConfigurationElementsWithPluginManagement()
        throws Exception
    {
        PomTestWrapper pom = buildPom( "plugin-exec-config-order/w-plugin-mgmt" );
        String prefix = "build/plugins[1]/executions[1]/configuration/";
        assertEquals( "one", pom.getValue( prefix + "stringParams/stringParam[1]" ) );
        assertEquals( "two", pom.getValue( prefix + "stringParams/stringParam[2]" ) );
        assertEquals( "three", pom.getValue( prefix + "stringParams/stringParam[3]" ) );
        assertEquals( "four", pom.getValue( prefix + "stringParams/stringParam[4]" ) );
        assertEquals( "key1", pom.getValue( prefix + "propertiesParam/property[1]/name" ) );
        assertEquals( "key2", pom.getValue( prefix + "propertiesParam/property[2]/name" ) );
    }
