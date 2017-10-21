    public void testMissingAll()
        throws Exception
    {
        SimpleProblemCollector result = validate( "missing-1-pom.xml" );

        assertViolations( result, 0, 4, 0 );

        List<String> messages = result.getErrors();

        assertTrue( messages.contains( "\'modelVersion\' is missing." ) );
        assertTrue( messages.contains( "\'groupId\' is missing." ) );
        assertTrue( messages.contains( "\'artifactId\' is missing." ) );
        assertTrue( messages.contains( "\'version\' is missing." ) );
        // type is inherited from the super pom
    }
