    public void testBadPluginDependencyScope()
        throws Exception
    {
        SimpleProblemCollector result = validate( "bad-plugin-dependency-scope.xml" );

        assertViolations( result, 0, 3, 0 );

        assertTrue( result.getErrors().get( 0 ).contains( "test:d" ) );

        assertTrue( result.getErrors().get( 1 ).contains( "test:e" ) );

        assertTrue( result.getErrors().get( 2 ).contains( "test:f" ) );
    }
