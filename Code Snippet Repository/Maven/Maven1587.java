    public void testReservedRepositoryId()
        throws Exception
    {
        SimpleProblemCollector result = validate( "reserved-repository-id.xml" );

        assertViolations( result, 0, 0, 4 );

        assertContains( result.getWarnings().get( 0 ), "'repositories.repository.id'" + " must not be 'local'" );
        assertContains( result.getWarnings().get( 1 ), "'pluginRepositories.pluginRepository.id' must not be 'local'" );
        assertContains( result.getWarnings().get( 2 ), "'distributionManagement.repository.id' must not be 'local'" );
        assertContains( result.getWarnings().get( 3 ),
                        "'distributionManagement.snapshotRepository.id' must not be 'local'" );
    }
