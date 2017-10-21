    public void testSystemPathRefersToProjectBasedir()
        throws Exception
    {
        SimpleProblemCollector result = validateRaw( "basedir-system-path.xml" );

        assertViolations( result, 0, 0, 2 );

        assertContains( result.getWarnings().get( 0 ),
                "'dependencies.dependency.systemPath' for test:a:jar should not point at files within the project directory" );
        assertContains( result.getWarnings().get( 1 ),
                "'dependencies.dependency.systemPath' for test:b:jar should not point at files within the project directory" );

        SimpleProblemCollector result_31 = validateRaw( "basedir-system-path.xml", ModelBuildingRequest.VALIDATION_LEVEL_MAVEN_3_1 );

        assertViolations( result_31, 0, 0, 4 );

        assertContains( result_31.getWarnings().get( 0 ),
                "'dependencies.dependency.scope' for test:a:jar declares usage of deprecated 'system' scope" );
        assertContains( result_31.getWarnings().get( 1 ),
                "'dependencies.dependency.systemPath' for test:a:jar should not point at files within the project directory" );
        assertContains( result_31.getWarnings().get( 2 ),
                "'dependencies.dependency.scope' for test:b:jar declares usage of deprecated 'system' scope" );
        assertContains( result_31.getWarnings().get( 3 ),
                "'dependencies.dependency.systemPath' for test:b:jar should not point at files within the project directory" );
    }
