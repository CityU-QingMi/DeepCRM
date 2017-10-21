	@Test
	public void testChildRevColumnType() {
		// We need the second column
		Iterator childEntityKeyColumnsIterator = metadata()
				.getEntityBinding("org.hibernate.envers.test.integration.inheritance.joined.ChildEntity_AUD" )
				.getKey()
				.getColumnIterator();
		childEntityKeyColumnsIterator.next();
		Column second = (Column) childEntityKeyColumnsIterator.next();

		assertEquals( second.getSqlType(), "int" );
	}
