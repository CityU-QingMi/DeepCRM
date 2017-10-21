	@Override
	protected IdTableInfoImpl buildIdTableInfo(
			PersistentClass entityBinding,
			Table idTable,
			JdbcServices jdbcServices,
			MetadataImplementor metadata,
			PreparationContextImpl context) {
		context.creationStatements.add( buildIdTableCreateStatement( idTable, jdbcServices, metadata ) );
		if ( dropIdTables ) {
			context.dropStatements.add( buildIdTableDropStatement( idTable, jdbcServices ) );
		}

		final String renderedName = jdbcServices.getJdbcEnvironment().getQualifiedObjectNameFormatter().format(
				idTable.getQualifiedTableName(),
				jdbcServices.getJdbcEnvironment().getDialect()
		);

		return new IdTableInfoImpl( renderedName );
	}
