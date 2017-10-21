	@Override
	protected String generateDeleteString() {
		final Update update = new Update( getDialect() )
				.setTableName( qualifiedTableName )
				.addColumns( keyColumnNames, "null" )
				.addPrimaryKeyColumns( keyColumnNames );

		if ( hasIndex && !indexContainsFormula ) {
			for ( int i = 0 ; i < indexColumnNames.length ; i++ ) {
				if ( indexColumnIsSettable[i] ) {
					update.addColumn( indexColumnNames[i], "null" );
				}
			}
		}

		if ( hasWhere ) {
			update.setWhere( sqlWhereString );
		}

		if ( getFactory().getSessionFactoryOptions().isCommentsEnabled() ) {
			update.setComment( "delete one-to-many " + getRole() );
		}

		return update.toStatementString();
	}
