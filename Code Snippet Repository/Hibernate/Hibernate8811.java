	@Test
	@TestForIssue( jiraKey = "")
	public void testManyToManyForeignKeys() {
		final Collection ownerCollectionMapping = metadata.getCollectionBinding(
				Category.class.getName() + "." + "items"
		);
		final String expectedOwnerFK = transformEntityName( Category.class.getName() ) + "_id";
		final String expectedInverseFK = transformEntityName( Item.class.getName() ) + "_items_id";

		boolean ownerFKFound = false;
		boolean inverseFKFound = false;
		for ( Iterator it = ownerCollectionMapping.getCollectionTable().getForeignKeyIterator(); it.hasNext(); ) {
			final String fkColumnName = ( (ForeignKey) it.next() ).getColumn( 0 ).getName();
			if ( expectedOwnerFK.equals( fkColumnName ) ) {
				ownerFKFound = true;
			}
			else if ( expectedInverseFK.equals( fkColumnName ) ) {
				inverseFKFound = true;
			}
		}
		assertTrue( ownerFKFound );
		assertTrue( inverseFKFound );
	}
