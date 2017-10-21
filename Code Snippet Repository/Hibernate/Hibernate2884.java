	@Override
	protected void finishPreparation(
			JdbcServices jdbcServices,
			JdbcConnectionAccess connectionAccess,
			MetadataImplementor metadata,
			PreparationContextImpl context) {
		IdTableHelper.INSTANCE.executeIdTableCreationStatements(
				context.creationStatements,
				jdbcServices,
				connectionAccess
		);

		this.dropTableStatements = dropIdTables
				? context.dropStatements.toArray( new String[ context.dropStatements.size() ] )
				: null;
	}
