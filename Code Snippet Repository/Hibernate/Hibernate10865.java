	@SuppressWarnings({""})
	@Test
	public void testJoinColumnNames() {
		Iterator<Column> columns = metadata().getEntityBinding(
				"org.hibernate.envers.test.integration.naming.ids.JoinEmbIdNamingRefIngEntity_AUD"
		).getProperty( "reference_x" ).getColumnIterator();

		assertTrue( columns.hasNext() );
		assertEquals( "XX_reference", columns.next().getName() );
		assertFalse( columns.hasNext() );

		columns = metadata().getEntityBinding(
				"org.hibernate.envers.test.integration.naming.ids.JoinEmbIdNamingRefIngEntity_AUD"
		).getProperty( "reference_y" ).getColumnIterator();

		assertTrue( columns.hasNext() );
		assertEquals( "YY_reference", columns.next().getName() );
		assertFalse( columns.hasNext() );
	}
