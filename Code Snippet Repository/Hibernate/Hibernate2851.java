	@Override
	public void release(
			JdbcServices jdbcServices,
			JdbcConnectionAccess connectionAccess) {
		if ( ! dropIdTables ) {
			return;
		}

		IdTableHelper.INSTANCE.executeIdTableDropStatements( dropTableStatements, jdbcServices, connectionAccess );
	}
