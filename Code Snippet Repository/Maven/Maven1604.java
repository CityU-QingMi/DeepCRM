    public void testEmptyPluginVersion()
        throws Exception
    {
        SimpleProblemCollector result = validate( "empty-plugin-version.xml" );

        assertViolations( result, 0, 1, 0 );

        assertEquals( "'build.plugins.plugin.version' for org.apache.maven.plugins:maven-it-plugin"
            + " must be a valid version but is ''.", result.getErrors().get( 0 ) );
    }
