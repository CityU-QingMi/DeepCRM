	@SuppressWarnings({""})
	@Test
	public void testJoinColumnName() {
		Iterator<Column> columns = metadata().getEntityBinding(
				"org.hibernate.envers.test.integration.naming.JoinNamingRefIngEntity_AUD"
		).getProperty( "reference_id" ).getColumnIterator();
		assertTrue( columns.hasNext() );
		assertEquals( "jnree_column_reference", columns.next().getName() );
		assertFalse( columns.hasNext() );
	}
