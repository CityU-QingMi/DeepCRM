    public void testBadDependencyScope()
        throws Exception
    {
        SimpleProblemCollector result = validate( "bad-dependency-scope.xml" );

        assertViolations( result, 0, 0, 2 );

        assertTrue( result.getWarnings().get( 0 ).contains( "test:f" ) );

        assertTrue( result.getWarnings().get( 1 ).contains( "test:g" ) );
    }
