	@Override
	protected IdTableInfoImpl buildIdTableInfo(
			PersistentClass entityBinding,
			Table idTable,
			JdbcServices jdbcServices,
			MetadataImplementor metadata,
			PreparationContextImpl context) {
		final String renderedName = jdbcServices.getJdbcEnvironment().getQualifiedObjectNameFormatter().format(
				idTable.getQualifiedTableName(),
				jdbcServices.getJdbcEnvironment().getDialect()
		);

		context.creationStatements.add( buildIdTableCreateStatement( idTable, jdbcServices, metadata ) );
		if ( dropIdTables ) {
			context.dropStatements.add( buildIdTableDropStatement( idTable, jdbcServices ) );
		}

		return new IdTableInfoImpl( renderedName );
	}
