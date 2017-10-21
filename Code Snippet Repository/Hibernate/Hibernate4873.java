	@Override
	public String[] getSqlCreateStrings(Index index, Metadata metadata) {
		final JdbcEnvironment jdbcEnvironment = metadata.getDatabase().getJdbcEnvironment();
		final String tableName = jdbcEnvironment.getQualifiedObjectNameFormatter().format(
				index.getTable().getQualifiedTableName(),
				dialect
		);

		final String indexNameForCreation;
		if ( dialect.qualifyIndexName() ) {
			indexNameForCreation = jdbcEnvironment.getQualifiedObjectNameFormatter().format(
					new QualifiedNameImpl(
							index.getTable().getQualifiedTableName().getCatalogName(),
							index.getTable().getQualifiedTableName().getSchemaName(),
							jdbcEnvironment.getIdentifierHelper().toIdentifier( index.getQuotedName( dialect ) )
					),
					jdbcEnvironment.getDialect()
			);
		}
		else {
			indexNameForCreation = index.getName();
		}
		final StringBuilder buf = new StringBuilder()
				.append( "create index " )
				.append( indexNameForCreation )
				.append( " on " )
				.append( tableName )
				.append( " (" );

		boolean first = true;
		Iterator<Column> columnItr = index.getColumnIterator();
		while ( columnItr.hasNext() ) {
			final Column column = columnItr.next();
			if ( first ) {
				first = false;
			}
			else {
				buf.append( ", " );
			}
			buf.append( ( column.getQuotedName( dialect ) ) );
		}
		buf.append( ")" );
		return new String[] { buf.toString() };
	}
