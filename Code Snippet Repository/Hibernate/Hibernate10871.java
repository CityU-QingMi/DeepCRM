	@SuppressWarnings({""})
	@Test
	public void testJoinColumnNames() {
		Iterator<Column> columns = metadata().getEntityBinding(
				"org.hibernate.envers.test.integration.naming.ids.JoinMulIdNamingRefIngEntity_AUD"
		).getProperty( "reference_id1" ).getColumnIterator();

		assertTrue( columns.hasNext() );
		assertEquals( "ID1_reference", columns.next().getName() );
		assertFalse( columns.hasNext() );

		columns = metadata().getEntityBinding(
				"org.hibernate.envers.test.integration.naming.ids.JoinMulIdNamingRefIngEntity_AUD"
		).getProperty( "reference_id2" ).getColumnIterator();

		assertTrue( columns.hasNext() );
		assertEquals( "ID2_reference", columns.next().getName() );
		assertFalse( columns.hasNext() );
	}
