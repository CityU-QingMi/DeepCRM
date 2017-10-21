	@Test
	public void testHistoryOfEdId1() {
		SetOwningEntity ing1 = getEntityManager().find( SetOwningEntity.class, ing1_id );
		SetOwningEntity ing2 = getEntityManager().find( SetOwningEntity.class, ing2_id );

		SetOwnedEntity rev1 = getAuditReader().find( SetOwnedEntity.class, ed1_id, 1 );
		SetOwnedEntity rev2 = getAuditReader().find( SetOwnedEntity.class, ed1_id, 2 );
		SetOwnedEntity rev3 = getAuditReader().find( SetOwnedEntity.class, ed1_id, 3 );
		SetOwnedEntity rev4 = getAuditReader().find( SetOwnedEntity.class, ed1_id, 4 );
		SetOwnedEntity rev5 = getAuditReader().find( SetOwnedEntity.class, ed1_id, 5 );

		assert rev1.getReferencing().equals( Collections.EMPTY_SET );
		assert rev2.getReferencing().equals( TestTools.makeSet( ing1, ing2 ) );
		assert rev3.getReferencing().equals( TestTools.makeSet( ing1, ing2 ) );
		assert rev4.getReferencing().equals( TestTools.makeSet( ing2 ) );
		assert rev5.getReferencing().equals( TestTools.makeSet( ing2 ) );
	}
