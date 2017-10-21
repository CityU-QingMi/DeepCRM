	private InformationExtractorJdbcDatabaseMetaDataImplTest buildInformationExtractorJdbcDatabaseMetaDataImplTest()
			throws SQLException {
		Database database = metadata.getDatabase();

		final ConnectionProvider connectionProvider = ssr.getService( ConnectionProvider.class );
		DatabaseInformation dbInfo = new DatabaseInformationImpl(
				ssr,
				database.getJdbcEnvironment(),
				new DdlTransactionIsolatorTestingImpl( ssr,
													   new JdbcEnvironmentInitiator.ConnectionProviderJdbcConnectionAccess(
															   connectionProvider )
				),
				database.getDefaultNamespace().getName()
		);
		ExtractionContextImpl extractionContext = new ExtractionContextImpl(
				ssr,
				database.getJdbcEnvironment(),
				ssr.getService( JdbcServices.class ).getBootstrapJdbcConnectionAccess(),
				(ExtractionContext.DatabaseObjectAccess) dbInfo,
				database.getDefaultNamespace().getPhysicalName().getCatalog(),
				database.getDefaultNamespace().getPhysicalName().getSchema()

		);
		return new InformationExtractorJdbcDatabaseMetaDataImplTest(
				extractionContext );
	}
