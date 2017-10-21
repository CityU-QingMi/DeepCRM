	@Test
	@TestForIssue( jiraKey = "" )
	public void testConstraintNameLength() {
		int foundCount = 0;
		for ( Namespace namespace : metadata().getDatabase().getNamespaces() ) {
			for ( org.hibernate.mapping.Table table : namespace.getTables() ) {
				Iterator fkItr = table.getForeignKeyIterator();
				while (fkItr.hasNext()) {
					ForeignKey fk = (ForeignKey) fkItr.next();
					assertTrue( fk.getName().length() <= MAX_NAME_LENGTH );

					// ensure the randomly generated constraint name doesn't
					// happen if explicitly given
					Column column = fk.getColumn( 0 );
					if ( column.getName().equals( "explicit_native" ) ) {
						foundCount++;
						assertEquals( fk.getName(), EXPLICIT_FK_NAME_NATIVE );
					}
					else if ( column.getName().equals( "explicit_jpa" ) ) {
						foundCount++;
						assertEquals( fk.getName(), EXPLICIT_FK_NAME_JPA );
					}
				}

				Iterator ukItr = table.getUniqueKeyIterator();
				while (ukItr.hasNext()) {
					UniqueKey uk = (UniqueKey) ukItr.next();
					assertTrue( uk.getName().length() <= MAX_NAME_LENGTH );

					// ensure the randomly generated constraint name doesn't
					// happen if explicitly given
					Column column = uk.getColumn( 0 );
					if ( column.getName().equals( "explicit" ) ) {
						foundCount++;
						assertEquals( uk.getName(), EXPLICIT_UK_NAME );
					}
				}
			}

		}
		
		assertEquals("Could not find the necessary columns.", 3, foundCount);
	}
