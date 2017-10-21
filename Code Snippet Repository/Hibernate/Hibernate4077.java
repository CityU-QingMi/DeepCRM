	@Override
	protected String generateInsertRowString() {
		final Insert insert = new Insert( getDialect() )
				.setTableName( qualifiedTableName )
				.addColumns( keyColumnNames );

		if ( hasIdentifier ) {
			insert.addColumn( identifierColumnName );
		}

		if ( hasIndex /*&& !indexIsFormula*/ ) {
			insert.addColumns( indexColumnNames, indexColumnIsSettable );
		}

		if ( getFactory().getSessionFactoryOptions().isCommentsEnabled() ) {
			insert.setComment( "insert collection row " + getRole() );
		}

		//if ( !elementIsFormula ) {
		insert.addColumns( elementColumnNames, elementColumnIsSettable, elementColumnWriters );
		//}

		return insert.toStatementString();
	}
