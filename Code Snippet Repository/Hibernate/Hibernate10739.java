	@Test
	public void testHistoryOfEdIng2() {
		StrTestEntity ed1 = getEntityManager().find( StrTestEntity.class, ed1_id );
		StrTestEntity ed2 = getEntityManager().find( StrTestEntity.class, ed2_id );

		ListUniEntity rev1 = getAuditReader().find( ListUniEntity.class, ing2_id, 1 );
		ListUniEntity rev2 = getAuditReader().find( ListUniEntity.class, ing2_id, 2 );
		ListUniEntity rev3 = getAuditReader().find( ListUniEntity.class, ing2_id, 3 );
		ListUniEntity rev4 = getAuditReader().find( ListUniEntity.class, ing2_id, 4 );
		ListUniEntity rev5 = getAuditReader().find( ListUniEntity.class, ing2_id, 5 );

		assert rev1.getReferences().equals( Collections.EMPTY_LIST );
		assert TestTools.checkCollection( rev2.getReferences(), ed1, ed2 );
		assert TestTools.checkCollection( rev3.getReferences(), ed1, ed2 );
		assert TestTools.checkCollection( rev4.getReferences(), ed1, ed2 );
		assert TestTools.checkCollection( rev5.getReferences(), ed1, ed2 );
	}
