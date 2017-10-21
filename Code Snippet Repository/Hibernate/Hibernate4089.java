	@Override
	protected String generateUpdateRowString() {
		final Update update = new Update( getDialect() ).setTableName( qualifiedTableName );
		update.addPrimaryKeyColumns( elementColumnNames, elementColumnIsSettable, elementColumnWriters );
		if ( hasIdentifier ) {
			update.addPrimaryKeyColumns( new String[] {identifierColumnName} );
		}
		if ( hasIndex && !indexContainsFormula ) {
			for ( int i = 0 ; i < indexColumnNames.length ; i++ ) {
				if ( indexColumnIsSettable[i] ) {
					update.addColumn( indexColumnNames[i] );
				}
			}
		}

		return update.toStatementString();
	}
