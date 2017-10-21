    public void testPluginConfigurationUsingAttributesWithPluginManagementAndProfile()
        throws Exception
    {
        PomTestWrapper pom = buildPom( "plugin-config-attributes/w-profile", "maven-core-it" );
        assertEquals( "src", pom.getValue( "build/plugins[1]/configuration/domParam/copy/@todir" ) );
        assertEquals( "true", pom.getValue( "build/plugins[1]/configuration/domParam/copy/@overwrite" ) );
        assertEquals( "target", pom.getValue( "build/plugins[1]/configuration/domParam/copy/fileset/@dir" ) );
        assertEquals( null, pom.getValue( "build/plugins[1]/configuration/domParam/copy/fileset/@todir" ) );
        assertEquals( null, pom.getValue( "build/plugins[1]/configuration/domParam/copy/fileset/@overwrite" ) );
    }
