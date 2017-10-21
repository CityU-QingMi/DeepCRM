	@Test
	public void testHistoryOfEdId2() {
		MapOwningEntity ing2 = getEntityManager().find( MapOwningEntity.class, ing2_id );

		MapOwnedEntity rev1 = getAuditReader().find( MapOwnedEntity.class, ed2_id, 1 );
		MapOwnedEntity rev2 = getAuditReader().find( MapOwnedEntity.class, ed2_id, 2 );
		MapOwnedEntity rev3 = getAuditReader().find( MapOwnedEntity.class, ed2_id, 3 );

		assert rev1.getReferencing().equals( TestTools.makeSet( ing2 ) );
		assert rev2.getReferencing().equals( Collections.EMPTY_SET );
		assert rev3.getReferencing().equals( TestTools.makeSet( ing2 ) );
	}
