    public void testPluginConfigurationUsingAttributesWithoutPluginManagement()
        throws Exception
    {
        PomTestWrapper pom = buildPom( "plugin-config-attributes/wo-plugin-mgmt" );
        assertEquals( "src", pom.getValue( "build/plugins[1]/configuration/domParam/copy/@todir" ) );
        assertEquals( "true", pom.getValue( "build/plugins[1]/configuration/domParam/copy/@overwrite" ) );
        assertEquals( "target", pom.getValue( "build/plugins[1]/configuration/domParam/copy/fileset/@dir" ) );
        assertEquals( null, pom.getValue( "build/plugins[1]/configuration/domParam/copy/fileset/@todir" ) );
        assertEquals( null, pom.getValue( "build/plugins[1]/configuration/domParam/copy/fileset/@overwrite" ) );
    }
