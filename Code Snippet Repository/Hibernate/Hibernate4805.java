	@Override
	public boolean catalogExists(Identifier catalog) {
		try {
			final ResultSet resultSet = extractionContext.getJdbcDatabaseMetaData().getCatalogs();

			try {
				while ( resultSet.next() ) {
					final String existingCatalogName = resultSet.getString( "TABLE_CAT" );

					// todo : hmm.. case sensitive or insensitive match...
					// for now, match any case...

					if ( catalog.getText().equalsIgnoreCase( existingCatalogName ) ) {
						return true;
					}
				}

				return false;
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
			throw convertSQLException( sqlException, "Unable to query DatabaseMetaData for existing catalogs" );
		}
	}
