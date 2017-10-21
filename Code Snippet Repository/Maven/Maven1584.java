    public void testHardCodedSystemPath()
        throws Exception
    {
        SimpleProblemCollector result = validateRaw( "hard-coded-system-path.xml" );

        assertViolations( result, 0, 0, 1 );

        assertContains( result.getWarnings().get( 0 ),
                "'dependencies.dependency.systemPath' for test:a:jar should use a variable instead of a hard-coded path" );

        SimpleProblemCollector result_31 = validateRaw( "hard-coded-system-path.xml", ModelBuildingRequest.VALIDATION_LEVEL_MAVEN_3_1 );

        assertViolations( result_31, 0, 0, 3 );

        assertContains( result_31.getWarnings().get( 0 ),
                "'dependencies.dependency.scope' for test:a:jar declares usage of deprecated 'system' scope" );
        assertContains( result_31.getWarnings().get( 1 ),
                "'dependencies.dependency.systemPath' for test:a:jar should use a variable instead of a hard-coded path" );
        assertContains( result_31.getWarnings().get( 2 ),
                "'dependencies.dependency.scope' for test:b:jar declares usage of deprecated 'system' scope" );

    }
