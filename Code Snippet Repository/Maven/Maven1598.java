    public void testInvalidGroupAndArtifactIdInPluginManagement()
        throws Exception
    {
        SimpleProblemCollector result = validateRaw( "raw-model/missing-ga-pluginManagement.xml" );

        assertViolations( result, 2, 0, 0 );

        assertEquals( "'build.pluginManagement.plugins.plugin.(groupId:artifactId)' groupId of a plugin must be defined. ",
                      result.getFatals().get( 0 ) );

        assertEquals( "'build.pluginManagement.plugins.plugin.(groupId:artifactId)' artifactId of a plugin must be defined. ",
                      result.getFatals().get( 1 ) );

    }
