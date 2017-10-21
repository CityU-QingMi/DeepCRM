	protected Update generateUpdate(
			String tableName,
			String[] columnNames,
			String idSubselect,
			String comment) {
		final Update update = new Update( factory().getServiceRegistry().getService( JdbcServices.class ).getDialect() )
				.setTableName( tableName )
				.setWhere( "(" + String.join( ", ", (CharSequence[]) columnNames ) + ") in (" + idSubselect + ")" );
		if ( factory().getSessionFactoryOptions().isCommentsEnabled() ) {
			update.setComment( comment );
		}
		return update;
	}
