	protected void checkDefaultJoinColumnName(
			Class<?> ownerEntityClass,
			String ownerCollectionPropertyName,
			String ownerForeignKeyNameExpected) {
		final org.hibernate.mapping.Collection ownerCollection = metadata().getCollectionBinding(
				ownerEntityClass.getName() + '.' + ownerCollectionPropertyName
		);
		// The default owner join column can only be computed if it has a PK with 1 column.
		assertEquals ( 1, ownerCollection.getOwner().getKey().getColumnSpan() );
		assertEquals( ownerForeignKeyNameExpected, ownerCollection.getKey().getColumnIterator().next().getText() );

		boolean hasOwnerFK = false;
		for ( Iterator it=ownerCollection.getCollectionTable().getForeignKeyIterator(); it.hasNext(); ) {
			final ForeignKey fk = (ForeignKey) it.next();
			assertSame( ownerCollection.getCollectionTable(), fk.getTable() );
			if ( fk.getColumnSpan() > 1 ) {
				continue;
			}
			if ( fk.getColumn( 0 ).getText().equals( ownerForeignKeyNameExpected ) ) {
				assertSame( ownerCollection.getOwner().getTable(), fk.getReferencedTable() );
				hasOwnerFK = true;
			}
		}
		assertTrue( hasOwnerFK );
	}
