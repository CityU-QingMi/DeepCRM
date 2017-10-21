	@Test
	public void testHistoryOfEdIng1() {
		SetOwnedEntity ed1 = getEntityManager().find( SetOwnedEntity.class, ed1_id );
		SetOwnedEntity ed2 = getEntityManager().find( SetOwnedEntity.class, ed2_id );

		SetOwningEntity rev1 = getAuditReader().find( SetOwningEntity.class, ing1_id, 1 );
		SetOwningEntity rev2 = getAuditReader().find( SetOwningEntity.class, ing1_id, 2 );
		SetOwningEntity rev3 = getAuditReader().find( SetOwningEntity.class, ing1_id, 3 );
		SetOwningEntity rev4 = getAuditReader().find( SetOwningEntity.class, ing1_id, 4 );
		SetOwningEntity rev5 = getAuditReader().find( SetOwningEntity.class, ing1_id, 5 );

		assert rev1.getReferences().equals( Collections.EMPTY_SET );
		assert rev2.getReferences().equals( TestTools.makeSet( ed1 ) );
		assert rev3.getReferences().equals( TestTools.makeSet( ed1, ed2 ) );
		assert rev4.getReferences().equals( TestTools.makeSet( ed2 ) );
		assert rev5.getReferences().equals( Collections.EMPTY_SET );
	}
