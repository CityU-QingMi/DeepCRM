	@Test
	public void testHistoryOfEdIng2() {
		ListOwnedEntity ed1 = getEntityManager().find( ListOwnedEntity.class, ed1_id );
		ListOwnedEntity ed2 = getEntityManager().find( ListOwnedEntity.class, ed2_id );

		ListOwningEntity rev1 = getAuditReader().find( ListOwningEntity.class, ing2_id, 1 );
		ListOwningEntity rev2 = getAuditReader().find( ListOwningEntity.class, ing2_id, 2 );
		ListOwningEntity rev3 = getAuditReader().find( ListOwningEntity.class, ing2_id, 3 );
		ListOwningEntity rev4 = getAuditReader().find( ListOwningEntity.class, ing2_id, 4 );
		ListOwningEntity rev5 = getAuditReader().find( ListOwningEntity.class, ing2_id, 5 );

		assert rev1.getReferences().equals( Collections.EMPTY_LIST );
		assert TestTools.checkCollection( rev2.getReferences(), ed1, ed2 );
		assert TestTools.checkCollection( rev3.getReferences(), ed1, ed2 );
		assert TestTools.checkCollection( rev4.getReferences(), ed1, ed2 );
		assert TestTools.checkCollection( rev5.getReferences(), ed1, ed2 );
	}
