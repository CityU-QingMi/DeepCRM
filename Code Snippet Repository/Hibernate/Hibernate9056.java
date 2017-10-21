	@Test
	public void testForeignKeyCreation() {
		PersistentClass classMapping = metadata().getEntityBinding( Account.class.getName() );
		
		Iterator foreignKeyIterator = classMapping.getTable().getForeignKeyIterator();
		boolean found = false;
		while ( foreignKeyIterator.hasNext() ) {
			ForeignKey element = (ForeignKey) foreignKeyIterator.next();
			if(element.getReferencedEntityName().equals(Person.class.getName() ) ) {
				
				if(!element.isReferenceToPrimaryKey() ) {
					List referencedColumns = element.getReferencedColumns();
					Column column = (Column) referencedColumns.get(0);
					if(column.getName().equals("person_userid") ) {
						found = true; // extend test to include the columns
					}				
				}
			}
		}
		
		assertTrue("Property ref foreign key not found",found);
	}
