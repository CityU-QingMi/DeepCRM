	@Test
	@TestForIssue( jiraKey = "" )
	public void testUniqueConstraints() {
		Column column = (Column) metadata().getEntityBinding( DataPoint.class.getName() )
				.getProperty( "foo1" ).getColumnIterator().next();
		assertFalse( column.isNullable() );
		assertTrue( column.isUnique() );

		column = (Column) metadata().getEntityBinding( DataPoint.class.getName() )
				.getProperty( "foo2" ).getColumnIterator().next();
		assertTrue( column.isNullable() );
		assertTrue( column.isUnique() );

		column = (Column) metadata().getEntityBinding( DataPoint.class.getName() )
				.getProperty( "id" ).getColumnIterator().next();
		assertFalse( column.isNullable() );
		assertTrue( column.isUnique() );
	}
