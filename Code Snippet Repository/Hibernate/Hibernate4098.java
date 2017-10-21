	private String generateVersionIncrementUpdateString() {
		Update update = new Update( getFactory().getDialect() );
		update.setTableName( getTableName( 0 ) );
		if ( getFactory().getSessionFactoryOptions().isCommentsEnabled() ) {
			update.setComment( "forced version increment" );
		}
		update.addColumn( getVersionColumnName() );
		update.addPrimaryKeyColumns( getIdentifierColumnNames() );
		update.setVersionColumnName( getVersionColumnName() );
		return update.toStatementString();
	}
