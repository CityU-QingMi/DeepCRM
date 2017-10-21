	@Override
	protected String generateUpdateRowString() {
		final Update update = new Update( getDialect() )
				.setTableName( qualifiedTableName );

		//if ( !elementIsFormula ) {
		update.addColumns( elementColumnNames, elementColumnIsSettable, elementColumnWriters );
		//}

		if ( hasIdentifier ) {
			update.addPrimaryKeyColumns( new String[] {identifierColumnName} );
		}
		else if ( hasIndex && !indexContainsFormula ) {
			update.addPrimaryKeyColumns( ArrayHelper.join( keyColumnNames, indexColumnNames ) );
		}
		else {
			update.addPrimaryKeyColumns( keyColumnNames );
			update.addPrimaryKeyColumns( elementColumnNames, elementColumnIsInPrimaryKey, elementColumnWriters );
		}

		if ( getFactory().getSessionFactoryOptions().isCommentsEnabled() ) {
			update.setComment( "update collection row " + getRole() );
		}

		return update.toStatementString();
	}
