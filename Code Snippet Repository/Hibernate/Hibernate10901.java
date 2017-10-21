	@Test
	public void testHistoryOfEdId1() {
		ListRefIngEntity ing1 = getEntityManager().find( ListRefIngEntity.class, ing1_id );
		ListRefIngEntity ing2 = getEntityManager().find( ListRefIngEntity.class, ing2_id );

		ListRefEdEntity rev1 = getAuditReader().find( ListRefEdEntity.class, ed1_id, 1 );
		ListRefEdEntity rev2 = getAuditReader().find( ListRefEdEntity.class, ed1_id, 2 );
		ListRefEdEntity rev3 = getAuditReader().find( ListRefEdEntity.class, ed1_id, 3 );

		assert TestTools.checkCollection( rev1.getReffering(), ing1, ing2 );
		assert TestTools.checkCollection( rev2.getReffering(), ing2 );
		assert TestTools.checkCollection( rev3.getReffering() );
	}
