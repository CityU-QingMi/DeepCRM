	@Override
	protected String generateDeleteString() {
		final Delete delete = new Delete()
				.setTableName( qualifiedTableName )
				.addPrimaryKeyColumns( keyColumnNames );

		if ( hasWhere ) {
			delete.setWhere( sqlWhereString );
		}

		if ( getFactory().getSessionFactoryOptions().isCommentsEnabled() ) {
			delete.setComment( "delete collection " + getRole() );
		}

		return delete.toStatementString();
	}
