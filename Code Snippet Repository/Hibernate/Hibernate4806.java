	@Override
	public boolean schemaExists(Identifier catalog, Identifier schema) {
		try {
			final String catalogFilter = determineCatalogFilter( catalog );
			final String schemaFilter = determineSchemaFilter( schema );

			final ResultSet resultSet = extractionContext.getJdbcDatabaseMetaData().getSchemas(
					catalogFilter,
					schemaFilter
			);

			try {
				if ( !resultSet.next() ) {
					return false;
				}

				if ( resultSet.next() ) {
					final String catalogName = catalog == null ? "" : catalog.getCanonicalName();
					final String schemaName = schema == null ? "" : schema.getCanonicalName();

					log.debugf(
							"Multiple schemas found with that name [%s.%s]",
							catalogName,
							schemaName
					);
				}
				return true;
			}
			finally {
				try {
					resultSet.close();
				}
				catch (SQLException ignore) {
				}
			}
		}
		catch (SQLException sqlException) {
			throw convertSQLException( sqlException, "Unable to query DatabaseMetaData for existing schemas" );
		}
	}
