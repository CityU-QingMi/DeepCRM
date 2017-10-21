    public void testInvalidArtifactIdInPluginManagement()
        throws Exception
    {
        SimpleProblemCollector result = validateRaw( "raw-model/missing-artifactId-pluginManagement.xml" );

        assertViolations( result, 1, 0, 0 );

        assertEquals( "'build.pluginManagement.plugins.plugin.(groupId:artifactId)' artifactId of a plugin must be defined. ",
                      result.getFatals().get( 0 ) );

    }
