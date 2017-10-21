	@Test
	@TestForIssue( jiraKey = "" )
	public void testNoJdbcMetadataDefaultDialect() {
		final StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySetting( "hibernate.temp.use_jdbc_metadata_defaults", "false" )
				.build();
		JdbcEnvironment jdbcEnvironment = serviceRegistry.getService( JdbcEnvironment.class );
		ExtractedDatabaseMetaData extractedDatabaseMetaData = jdbcEnvironment.getExtractedDatabaseMetaData();

		assertNull( extractedDatabaseMetaData.getConnectionCatalogName() );
		assertNull( extractedDatabaseMetaData.getConnectionSchemaName() );
		assertTrue( extractedDatabaseMetaData.getTypeInfoSet().isEmpty() );
		assertTrue( extractedDatabaseMetaData.getExtraKeywords().isEmpty() );
		assertFalse( extractedDatabaseMetaData.supportsNamedParameters() );
		assertFalse( extractedDatabaseMetaData.supportsRefCursors() );
		assertFalse( extractedDatabaseMetaData.supportsScrollableResults() );
		assertFalse( extractedDatabaseMetaData.supportsGetGeneratedKeys() );
		assertFalse( extractedDatabaseMetaData.supportsBatchUpdates() );
		assertFalse( extractedDatabaseMetaData.supportsDataDefinitionInTransaction() );
		assertFalse( extractedDatabaseMetaData.doesDataDefinitionCauseTransactionCommit() );
		assertNull( extractedDatabaseMetaData.getSqlStateType() );
		assertFalse( extractedDatabaseMetaData.doesLobLocatorUpdateCopy() );

		StandardServiceRegistryBuilder.destroy( serviceRegistry );
	}
