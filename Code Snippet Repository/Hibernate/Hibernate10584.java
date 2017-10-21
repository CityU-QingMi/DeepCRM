	@Test
	public void testRevisionHistory() {
		// revision - insertion
		final SimpleEntity rev1 = getAuditReader().find( SimpleEntity.class, entityId, 1 );
		assertEquals( "data", rev1.getData() );
		assertEquals( 1, rev1.getCaseNumberInsert() );

		// revision - update
		final SimpleEntity rev2 = getAuditReader().find( SimpleEntity.class, entityId, 2 );
		assertEquals( "data2", rev2.getData() );
		assertEquals( 1, rev2.getCaseNumberInsert() );

		// revision - deletion
		final SimpleEntity rev3 = getAuditReader().find( SimpleEntity.class, entityId, 3 );
		assertEquals( "data2", rev2.getData() );
		assertEquals( 1, rev2.getCaseNumberInsert() );
	}
