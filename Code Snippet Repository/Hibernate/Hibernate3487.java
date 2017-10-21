	protected boolean isDuplicateAssociation(
			final String lhsTable,
			final String[] lhsColumnNames,
			final AssociationType type) {
		final String foreignKeyTable;
		final String[] foreignKeyColumns;
		if ( type.getForeignKeyDirection() == ForeignKeyDirection.FROM_PARENT ) {
			foreignKeyTable = lhsTable;
			foreignKeyColumns = lhsColumnNames;
		}
		else {
			foreignKeyTable = type.getAssociatedJoinable( getFactory() ).getTableName();
			foreignKeyColumns = JoinHelper.getRHSColumnNames( type, getFactory() );
		}
		return isDuplicateAssociation( foreignKeyTable, foreignKeyColumns );
	}
