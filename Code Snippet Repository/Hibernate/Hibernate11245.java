	@Test
	public void testHistoryOfIng1() {
		SetOwningEntity ver_empty = createOwningEntity();
		SetOwningEntity ver_child = createOwningEntity( new SetOwnedEntity( ed_id, "child" ) );

		assertEquals( getAuditReader().find( SetOwningEntity.class, ing_id, 1 ), ver_empty );
		assertEquals( getAuditReader().find( SetOwningEntity.class, ing_id, 2 ), ver_child );
		assertEquals( getAuditReader().find( SetOwningEntity.class, ing_id, 3 ), ver_empty );
		assertEquals( getAuditReader().find( SetOwningEntity.class, ing_id, 4 ), ver_child );
		assertEquals( getAuditReader().find( SetOwningEntity.class, ing_id, 5 ), ver_empty );
	}
