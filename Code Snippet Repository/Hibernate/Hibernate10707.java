	@Test
	public void testHistoryOfEdId1() {
		MapOwningEntity ing1 = getEntityManager().find( MapOwningEntity.class, ing1_id );
		MapOwningEntity ing2 = getEntityManager().find( MapOwningEntity.class, ing2_id );

		MapOwnedEntity rev1 = getAuditReader().find( MapOwnedEntity.class, ed1_id, 1 );
		MapOwnedEntity rev2 = getAuditReader().find( MapOwnedEntity.class, ed1_id, 2 );
		MapOwnedEntity rev3 = getAuditReader().find( MapOwnedEntity.class, ed1_id, 3 );

		assert rev1.getReferencing().equals( Collections.EMPTY_SET );
		assert rev2.getReferencing().equals( TestTools.makeSet( ing1, ing2 ) );
		assert rev3.getReferencing().equals( Collections.EMPTY_SET );
	}
