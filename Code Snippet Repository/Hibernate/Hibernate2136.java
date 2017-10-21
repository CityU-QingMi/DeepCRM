		public Builder apply(DatabaseMetaData databaseMetaData) throws SQLException {
			connectionCatalogName = databaseMetaData.getConnection().getCatalog();
			// NOTE : databaseMetaData.getConnection().getSchema() would require java 1.7 as baseline
			supportsRefCursors = StandardRefCursorSupport.supportsRefCursors( databaseMetaData );
			supportsNamedParameters = databaseMetaData.supportsNamedParameters();
			supportsScrollableResults = databaseMetaData.supportsResultSetType( ResultSet.TYPE_SCROLL_INSENSITIVE );
			supportsGetGeneratedKeys = databaseMetaData.supportsGetGeneratedKeys();
			supportsBatchUpdates = databaseMetaData.supportsBatchUpdates();
			supportsDataDefinitionInTransaction = !databaseMetaData.dataDefinitionIgnoredInTransactions();
			doesDataDefinitionCauseTransactionCommit = databaseMetaData.dataDefinitionCausesTransactionCommit();
			extraKeywords = parseKeywords( databaseMetaData.getSQLKeywords() );
			sqlStateType = SQLStateType.interpretReportedSQLStateType( databaseMetaData.getSQLStateType() );
			lobLocatorUpdateCopy = databaseMetaData.locatorsUpdateCopy();
			typeInfoSet = new LinkedHashSet<TypeInfo>();
			typeInfoSet.addAll( TypeInfo.extractTypeInfo( databaseMetaData ) );
			return this;
		}
