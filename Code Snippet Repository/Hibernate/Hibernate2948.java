	@Override
	public void registerExportables(Database database) {
		final JdbcEnvironment jdbcEnvironment = database.getJdbcEnvironment();
		final Dialect dialect = jdbcEnvironment.getDialect();

		final Namespace namespace = database.locateNamespace(
				logicalQualifiedTableName.getCatalogName(),
				logicalQualifiedTableName.getSchemaName()
		);

		Table table = namespace.locateTable( logicalQualifiedTableName.getObjectName() );
		if ( table == null ) {
			table = namespace.createTable( logicalQualifiedTableName.getObjectName(), false );
		}

		this.tableNameText = jdbcEnvironment.getQualifiedObjectNameFormatter().format(
				table.getQualifiedTableName(),
				dialect
		);

		this.valueColumnNameText = logicalValueColumnNameIdentifier.render( dialect );


		this.selectQuery = "select " + valueColumnNameText + " as id_val" +
				" from " + dialect.appendLockHint( LockMode.PESSIMISTIC_WRITE, tableNameText ) +
				dialect.getForUpdateString();

		this.updateQuery = "update " + tableNameText +
				" set " + valueColumnNameText + "= ?" +
				" where " + valueColumnNameText + "=?";

		ExportableColumn valueColumn = new ExportableColumn(
				database,
				table,
				valueColumnNameText,
				LongType.INSTANCE
		);
		table.addColumn( valueColumn );

		table.addInitCommand(
				new InitCommand( "insert into " + tableNameText + " values ( " + initialValue + " )" )
		);
	}
