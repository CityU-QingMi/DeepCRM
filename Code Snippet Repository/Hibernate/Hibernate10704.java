	@Test
	public void testHistoryOfEdId2() {
		ListOwningEntity ing1 = getEntityManager().find( ListOwningEntity.class, ing1_id );
		ListOwningEntity ing2 = getEntityManager().find( ListOwningEntity.class, ing2_id );

		ListOwnedEntity rev1 = getAuditReader().find( ListOwnedEntity.class, ed2_id, 1 );
		ListOwnedEntity rev2 = getAuditReader().find( ListOwnedEntity.class, ed2_id, 2 );
		ListOwnedEntity rev3 = getAuditReader().find( ListOwnedEntity.class, ed2_id, 3 );
		ListOwnedEntity rev4 = getAuditReader().find( ListOwnedEntity.class, ed2_id, 4 );
		ListOwnedEntity rev5 = getAuditReader().find( ListOwnedEntity.class, ed2_id, 5 );

		assert rev1.getReferencing().equals( Collections.EMPTY_LIST );
		assert TestTools.checkCollection( rev2.getReferencing(), ing2 );
		assert TestTools.checkCollection( rev3.getReferencing(), ing1, ing2 );
		assert TestTools.checkCollection( rev4.getReferencing(), ing1, ing2 );
		assert TestTools.checkCollection( rev5.getReferencing(), ing2 );
	}
