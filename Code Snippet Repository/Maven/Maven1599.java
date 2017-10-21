    public void testDeprecatedDependencyMetaversionsLatestAndRelease()
            throws Exception
    {
        SimpleProblemCollector result = validateRaw( "deprecated-dependency-metaversions-latest-and-release.xml" );

        assertViolations( result, 0, 0, 2 );

        assertContains( result.getWarnings().get( 0 ),
               "'dependencies.dependency.version' for test:a:jar is either LATEST or RELEASE (both of them are being deprecated)" );
        assertContains( result.getWarnings().get( 1 ),
                "'dependencies.dependency.version' for test:b:jar is either LATEST or RELEASE (both of them are being deprecated)" );
    }
