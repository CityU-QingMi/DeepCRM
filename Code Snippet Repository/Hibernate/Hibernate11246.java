	@Test
	public void testHistoryOfEd1() {
		SetOwnedEntity ver_empty = createOwnedEntity();
		SetOwnedEntity ver_child = createOwnedEntity( new SetOwningEntity( ing_id, "parent" ) );

		assertEquals( getAuditReader().find( SetOwnedEntity.class, ed_id, 1 ), ver_empty );
		assertEquals( getAuditReader().find( SetOwnedEntity.class, ed_id, 2 ), ver_child );
		assertEquals( getAuditReader().find( SetOwnedEntity.class, ed_id, 3 ), ver_empty );
		assertEquals( getAuditReader().find( SetOwnedEntity.class, ed_id, 4 ), ver_child );
		assertEquals( getAuditReader().find( SetOwnedEntity.class, ed_id, 5 ), ver_empty );
	}
