	@Override
	public void registerExportables(Database database) {
		final Dialect dialect = database.getJdbcEnvironment().getDialect();

		final Namespace namespace = database.locateNamespace(
				qualifiedTableName.getCatalogName(),
				qualifiedTableName.getSchemaName()
		);

		Table table = namespace.locateTable( qualifiedTableName.getObjectName() );
		if ( table == null ) {
			table = namespace.createTable( qualifiedTableName.getObjectName(), false );

			// todo : note sure the best solution here.  do we add the columns if missing?  other?
			final Column segmentColumn = new ExportableColumn(
					database,
					table,
					segmentColumnName,
					StringType.INSTANCE,
					dialect.getTypeName( Types.VARCHAR, segmentValueLength, 0, 0 )
			);
			segmentColumn.setNullable( false );
			table.addColumn( segmentColumn );

			// lol
			table.setPrimaryKey( new PrimaryKey( table ) );
			table.getPrimaryKey().addColumn( segmentColumn );

			final Column valueColumn = new ExportableColumn(
					database,
					table,
					valueColumnName,
					LongType.INSTANCE
			);
			table.addColumn( valueColumn );
		}

		// allow physical naming strategies a chance to kick in
		this.renderedTableName = database.getJdbcEnvironment().getQualifiedObjectNameFormatter().format(
				table.getQualifiedTableName(),
				dialect
		);

		this.selectQuery = buildSelectQuery( dialect );
		this.updateQuery = buildUpdateQuery();
		this.insertQuery = buildInsertQuery();

	}
