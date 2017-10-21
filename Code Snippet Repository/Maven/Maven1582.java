    public void testBadPluginVersion()
        throws Exception
    {
        SimpleProblemCollector result = validate( "bad-plugin-version.xml" );

        assertViolations( result, 0, 4, 0 );

        assertContains( result.getErrors().get( 0 ),
                        "'build.plugins.plugin.version' for test:mip must be a valid version" );
        assertContains( result.getErrors().get( 1 ),
                        "'build.plugins.plugin.version' for test:rmv must be a valid version" );
        assertContains( result.getErrors().get( 2 ),
                        "'build.plugins.plugin.version' for test:lmv must be a valid version" );
        assertContains( result.getErrors().get( 3 ),
                        "'build.plugins.plugin.version' for test:ifsc must not contain any of these characters" );
    }
