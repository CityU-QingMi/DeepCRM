	@Override
	protected String generateDeleteRowString() {
		final Update update = new Update( getDialect() )
				.setTableName( qualifiedTableName )
				.addColumns( keyColumnNames, "null" );

		if ( hasIndex && !indexContainsFormula ) {
			for ( int i = 0 ; i < indexColumnNames.length ; i++ ) {
				if ( indexColumnIsSettable[i] ) {
					update.addColumn( indexColumnNames[i], "null" );
				}
			}
		}

		if ( getFactory().getSessionFactoryOptions().isCommentsEnabled() ) {
			update.setComment( "delete one-to-many row " + getRole() );
		}

		//use a combination of foreign key columns and pk columns, since
		//the ordering of removal and addition is not guaranteed when
		//a child moves from one parent to another
		String[] rowSelectColumnNames = ArrayHelper.join( keyColumnNames, elementColumnNames );
		return update.addPrimaryKeyColumns( rowSelectColumnNames )
				.toStatementString();
	}
