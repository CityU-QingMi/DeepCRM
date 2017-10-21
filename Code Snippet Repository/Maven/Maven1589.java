    public void testBadDependencyExclusionId()
        throws Exception
    {
        SimpleProblemCollector result =
            validateEffective( "bad-dependency-exclusion-id.xml", ModelBuildingRequest.VALIDATION_LEVEL_MAVEN_2_0 );

        assertViolations( result, 0, 0, 2 );

        assertContains( result.getWarnings().get( 0 ),
                        "'dependencies.dependency.exclusions.exclusion.groupId' for gid:aid:jar" );
        assertContains( result.getWarnings().get( 1 ),
                        "'dependencies.dependency.exclusions.exclusion.artifactId' for gid:aid:jar" );

        // MNG-3832: Aether (part of M3+) supports wildcard expressions for exclusions

        SimpleProblemCollector result_30 = validate( "bad-dependency-exclusion-id.xml" );

        assertViolations( result_30, 0, 0, 0 );

    }
