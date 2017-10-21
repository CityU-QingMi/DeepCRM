	@Override
	protected boolean isDuplicateAssociation(
			final String foreignKeyTable,
			final String[] foreignKeyColumns) {
		//disable a join back to this same association
		final boolean isSameJoin = oneToManyPersister.getTableName().equals( foreignKeyTable ) &&
				Arrays.equals( foreignKeyColumns, oneToManyPersister.getKeyColumnNames() );
		return isSameJoin ||
				super.isDuplicateAssociation( foreignKeyTable, foreignKeyColumns );
	}
