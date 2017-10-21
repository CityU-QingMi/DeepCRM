	private ExtractedDatabaseMetaDataImpl(
			JdbcEnvironment jdbcEnvironment,
			String connectionCatalogName,
			String connectionSchemaName,
			Set<String> extraKeywords,
			LinkedHashSet<TypeInfo> typeInfoSet,
			boolean supportsRefCursors,
			boolean supportsNamedParameters,
			boolean supportsScrollableResults,
			boolean supportsGetGeneratedKeys,
			boolean supportsBatchUpdates,
			boolean supportsDataDefinitionInTransaction,
			boolean doesDataDefinitionCauseTransactionCommit,
			SQLStateType sqlStateType,
			boolean lobLocatorUpdateCopy) {
		this.jdbcEnvironment = jdbcEnvironment;

		this.connectionCatalogName = connectionCatalogName;
		this.connectionSchemaName = connectionSchemaName;

		this.extraKeywords = extraKeywords != null
				? extraKeywords
				: Collections.<String>emptySet();
		this.typeInfoSet = typeInfoSet != null
				? typeInfoSet
				: new LinkedHashSet<TypeInfo>();

		this.supportsRefCursors = supportsRefCursors;
		this.supportsNamedParameters = supportsNamedParameters;
		this.supportsScrollableResults = supportsScrollableResults;
		this.supportsGetGeneratedKeys = supportsGetGeneratedKeys;
		this.supportsBatchUpdates = supportsBatchUpdates;
		this.supportsDataDefinitionInTransaction = supportsDataDefinitionInTransaction;
		this.doesDataDefinitionCauseTransactionCommit = doesDataDefinitionCauseTransactionCommit;
		this.sqlStateType = sqlStateType;
		this.lobLocatorUpdateCopy = lobLocatorUpdateCopy;
	}
