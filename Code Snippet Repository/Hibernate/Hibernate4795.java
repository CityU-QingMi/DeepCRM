	public DatabaseInformationImpl(
			ServiceRegistry serviceRegistry,
			JdbcEnvironment jdbcEnvironment,
			DdlTransactionIsolator ddlTransactionIsolator,
			Namespace.Name defaultNamespace) throws SQLException {
		this.jdbcEnvironment = jdbcEnvironment;

		this.extractionContext = new ImprovedExtractionContextImpl(
				serviceRegistry,
				jdbcEnvironment,
				ddlTransactionIsolator,
				defaultNamespace.getCatalog(),
				defaultNamespace.getSchema(),
				this
		);

		// todo : make this pluggable
		this.extractor = new InformationExtractorJdbcDatabaseMetaDataImpl( extractionContext );

		// because we do not have defined a way to locate sequence info by name
		initializeSequences();
	}
