	@Test
	public void testHistoryOfEdId2() {
		ListRefIngEntity ing1 = getEntityManager().find( ListRefIngEntity.class, ing1_id );
		ListRefIngEntity ing2 = getEntityManager().find( ListRefIngEntity.class, ing2_id );

		ListRefEdEntity rev1 = getAuditReader().find( ListRefEdEntity.class, ed2_id, 1 );
		ListRefEdEntity rev2 = getAuditReader().find( ListRefEdEntity.class, ed2_id, 2 );
		ListRefEdEntity rev3 = getAuditReader().find( ListRefEdEntity.class, ed2_id, 3 );

		assert TestTools.checkCollection( rev1.getReffering() );
		assert TestTools.checkCollection( rev2.getReffering(), ing1 );
		assert TestTools.checkCollection( rev3.getReffering(), ing1, ing2 );
	}
