    public void testInvalidGroupIdInPluginManagement()
        throws Exception
    {
        SimpleProblemCollector result = validateRaw( "raw-model/missing-groupId-pluginManagement.xml" );

        assertViolations( result, 1, 0, 0 );

        assertEquals( "'build.pluginManagement.plugins.plugin.(groupId:artifactId)' groupId of a plugin must be defined. ",
                      result.getFatals().get( 0 ) );

    }
