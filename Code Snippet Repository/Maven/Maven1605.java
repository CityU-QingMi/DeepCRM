    public void testMissingRepositoryId()
        throws Exception
    {
        SimpleProblemCollector result =
            validateRaw( "missing-repository-id-pom.xml", ModelBuildingRequest.VALIDATION_LEVEL_STRICT );

        assertViolations( result, 0, 4, 0 );

        assertEquals( "'repositories.repository.id' is missing.", result.getErrors().get( 0 ) );

        assertEquals( "'repositories.repository[null].url' is missing.", result.getErrors().get( 1 ) );

        assertEquals( "'pluginRepositories.pluginRepository.id' is missing.", result.getErrors().get( 2 ) );

        assertEquals( "'pluginRepositories.pluginRepository[null].url' is missing.", result.getErrors().get( 3 ) );
    }
