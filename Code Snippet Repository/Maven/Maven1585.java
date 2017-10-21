    public void testDuplicatePlugin()
        throws Exception
    {
        SimpleProblemCollector result = validateRaw( "duplicate-plugin.xml" );

        assertViolations( result, 0, 0, 4 );

        assertTrue( result.getWarnings().get( 0 ).contains( "duplicate declaration of plugin test:duplicate" ) );
        assertTrue( result.getWarnings().get( 1 ).contains( "duplicate declaration of plugin test:managed-duplicate" ) );
        assertTrue( result.getWarnings().get( 2 ).contains( "duplicate declaration of plugin profile:duplicate" ) );
        assertTrue( result.getWarnings().get( 3 ).contains( "duplicate declaration of plugin profile:managed-duplicate" ) );
    }
