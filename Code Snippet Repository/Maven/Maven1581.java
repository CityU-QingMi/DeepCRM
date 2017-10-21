    public void testBadDependencyVersion()
        throws Exception
    {
        SimpleProblemCollector result = validate( "bad-dependency-version.xml" );

        assertViolations( result, 0, 2, 0 );

        assertContains( result.getErrors().get( 0 ),
                        "'dependencies.dependency.version' for test:b:jar must be a valid version" );
        assertContains( result.getErrors().get( 1 ),
                        "'dependencies.dependency.version' for test:c:jar must not contain any of these characters" );
    }
