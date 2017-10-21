	public ImprovedExtractionContextImpl(
			ServiceRegistry serviceRegistry,
			JdbcEnvironment jdbcEnvironment,
			DdlTransactionIsolator ddlTransactionIsolator,
			Identifier defaultCatalog,
			Identifier defaultSchema,
			DatabaseObjectAccess databaseObjectAccess) {
		this.serviceRegistry = serviceRegistry;
		this.jdbcEnvironment = jdbcEnvironment;
		this.ddlTransactionIsolator = ddlTransactionIsolator;
		this.defaultCatalog = defaultCatalog;
		this.defaultSchema = defaultSchema;
		this.databaseObjectAccess = databaseObjectAccess;
	}
