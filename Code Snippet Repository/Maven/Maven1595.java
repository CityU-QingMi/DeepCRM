    public void testInvalidVersionInPluginManagement()
        throws Exception
    {
        SimpleProblemCollector result = validateRaw( "raw-model/missing-plugin-version-pluginManagement.xml" );

        assertViolations( result, 1, 0, 0 );

        assertEquals( "'build.pluginManagement.plugins.plugin.(groupId:artifactId)' version of a plugin must be defined. ",
                      result.getFatals().get( 0 ) );

    }
