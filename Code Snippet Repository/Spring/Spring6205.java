	public static TableMetaDataProvider createMetaDataProvider(DataSource dataSource, TableMetaDataContext context) {
		try {
			TableMetaDataProvider result = (TableMetaDataProvider) JdbcUtils.extractDatabaseMetaData(dataSource, databaseMetaData -> {
				String databaseProductName =
						JdbcUtils.commonDatabaseName(databaseMetaData.getDatabaseProductName());
				boolean accessTableColumnMetaData = context.isAccessTableColumnMetaData();
				TableMetaDataProvider provider;
				if ("Oracle".equals(databaseProductName)) {
					provider = new OracleTableMetaDataProvider(databaseMetaData,
							context.isOverrideIncludeSynonymsDefault());
				}
				else if ("HSQL Database Engine".equals(databaseProductName)) {
					provider = new HsqlTableMetaDataProvider(databaseMetaData);
				}
				else if ("PostgreSQL".equals(databaseProductName)) {
					provider = new PostgresTableMetaDataProvider(databaseMetaData);
				}
				else if ("Apache Derby".equals(databaseProductName)) {
					provider = new DerbyTableMetaDataProvider(databaseMetaData);
				}
				else {
					provider = new GenericTableMetaDataProvider(databaseMetaData);
				}
				if (logger.isDebugEnabled()) {
					logger.debug("Using " + provider.getClass().getSimpleName());
				}
				provider.initializeWithMetaData(databaseMetaData);
				if (accessTableColumnMetaData) {
					provider.initializeWithTableColumnMetaData(databaseMetaData, context.getCatalogName(),
							context.getSchemaName(), context.getTableName());
				}
				return provider;
			});
			return result;
		}
		catch (MetaDataAccessException ex) {
			throw new DataAccessResourceFailureException("Error retrieving database metadata", ex);
		}
	}
