	private TableInformation processTableResults(
			Identifier catalog,
			Identifier schema,
			Identifier tableName,
			ResultSet resultSet) throws SQLException {
		try {
			boolean found = false;
			TableInformation tableInformation = null;
			while ( resultSet.next() ) {
				if ( tableName.equals( Identifier.toIdentifier( resultSet.getString( "TABLE_NAME" ),
																tableName.isQuoted() ) ) ) {
					if ( found ) {
						log.multipleTablesFound( tableName.render() );
						final String catalogName = catalog == null ? "" : catalog.render();
						final String schemaName = schema == null ? "" : schema.render();
						throw new SchemaExtractionException(
								String.format(
										Locale.ENGLISH,
										"More than one table found in namespace (%s, %s) : %s",
										catalogName,
										schemaName,
										tableName.render()
								)
						);
					}
					else {
						found = true;
						tableInformation = extractTableInformation( resultSet );
						addColumns( tableInformation );
					}
				}
			}
			if ( !found ) {
				log.tableNotFound( tableName.render() );
			}
			return tableInformation;
		}
		finally {
			try {
				resultSet.close();
			}
			catch (SQLException ignore) {
			}
		}
	}
