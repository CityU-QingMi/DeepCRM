	private boolean checkForExistingForeignKey(ForeignKey foreignKey, TableInformation tableInformation) {
		if ( foreignKey.getName() == null || tableInformation == null ) {
			return false;
		}

		final String referencingColumn = foreignKey.getColumn( 0 ).getName();
		final String referencedTable = foreignKey.getReferencedTable().getName();

/**/
/**/
/**/
/**/
		Predicate<ColumnReferenceMapping> mappingPredicate = m -> {
			String existingReferencingColumn = m.getReferencingColumnMetadata().getColumnIdentifier().getText();
			String existingReferencedTable = m.getReferencedColumnMetadata().getContainingTableInformation().getName().getTableName().getCanonicalName();
			return referencingColumn.equals( existingReferencingColumn ) && referencedTable.equals( existingReferencedTable );
		};
		Stream<ForeignKeyInformation> keyStream = StreamSupport.stream( tableInformation.getForeignKeys().spliterator(), false );
		Stream<ColumnReferenceMapping> mappingStream = keyStream.flatMap( k -> StreamSupport.stream( k.getColumnReferenceMappings().spliterator(), false ) );
		boolean found = mappingStream.anyMatch( mappingPredicate );
		if ( found ) {
			return true;
		}

		// And at the end just compare the name of the key. If a key with the same name exists we assume the function is
		// also the same...
		return tableInformation.getForeignKey( Identifier.toIdentifier( foreignKey.getName() ) ) != null;
	}
