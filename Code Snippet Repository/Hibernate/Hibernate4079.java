	@Override
	protected String generateDeleteRowString() {
		final Delete delete = new Delete().setTableName( qualifiedTableName );

		if ( hasIdentifier ) {
			delete.addPrimaryKeyColumns( new String[] {identifierColumnName} );
		}
		else if ( hasIndex && !indexContainsFormula ) {
			delete.addPrimaryKeyColumns( ArrayHelper.join( keyColumnNames, indexColumnNames ) );
		}
		else {
			delete.addPrimaryKeyColumns( keyColumnNames );
			delete.addPrimaryKeyColumns( elementColumnNames, elementColumnIsInPrimaryKey, elementColumnWriters );
		}

		if ( getFactory().getSessionFactoryOptions().isCommentsEnabled() ) {
			delete.setComment( "delete collection row " + getRole() );
		}

		return delete.toStatementString();
	}
