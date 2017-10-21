	@Override
	protected String generateInsertRowString() {
		final Update update = new Update( getDialect() )
				.setTableName( qualifiedTableName )
				.addColumns( keyColumnNames );

		if ( hasIndex && !indexContainsFormula ) {
			for ( int i = 0 ; i < indexColumnNames.length ; i++ ) {
				if ( indexColumnIsSettable[i] ) {
					update.addColumn( indexColumnNames[i] );
				}
			}
		}

		//identifier collections not supported for 1-to-many

		if ( getFactory().getSessionFactoryOptions().isCommentsEnabled() ) {
			update.setComment( "create one-to-many row " + getRole() );
		}

		return update.addPrimaryKeyColumns( elementColumnNames, elementColumnWriters )
				.toStatementString();
	}
