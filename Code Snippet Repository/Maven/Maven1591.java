    public void testMissingDependencyExclusionId()
        throws Exception
    {
        SimpleProblemCollector result = validate( "missing-dependency-exclusion-id.xml" );

        assertViolations( result, 0, 0, 2 );

        assertContains( result.getWarnings().get( 0 ),
                        "'dependencies.dependency.exclusions.exclusion.groupId' for gid:aid:jar is missing" );
        assertContains( result.getWarnings().get( 1 ),
                        "'dependencies.dependency.exclusions.exclusion.artifactId' for gid:aid:jar is missing" );
    }
