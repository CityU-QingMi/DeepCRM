	@Test
	public void testHistoryOfWjte2() {
		IntNoAutoIdTestEntity ite1_1 = getEntityManager().find( IntNoAutoIdTestEntity.class, ite1_1_id );
		IntNoAutoIdTestEntity ite1_2 = getEntityManager().find( IntNoAutoIdTestEntity.class, ite1_2_id );
		IntNoAutoIdTestEntity ite2_2 = getEntityManager().find( IntNoAutoIdTestEntity.class, ite2_2_id );

		WhereJoinTableEntity rev1 = getAuditReader().find( WhereJoinTableEntity.class, wjte2_id, 1 );
		WhereJoinTableEntity rev2 = getAuditReader().find( WhereJoinTableEntity.class, wjte2_id, 2 );
		WhereJoinTableEntity rev3 = getAuditReader().find( WhereJoinTableEntity.class, wjte2_id, 3 );
		WhereJoinTableEntity rev4 = getAuditReader().find( WhereJoinTableEntity.class, wjte2_id, 4 );

		// Checking 1st list
		assert TestTools.checkCollection( rev1.getReferences1() );
		assert TestTools.checkCollection( rev2.getReferences1() );
		assert TestTools.checkCollection( rev3.getReferences1(), ite1_1, ite1_2 );
		assert TestTools.checkCollection( rev4.getReferences1(), ite1_1, ite1_2 );

		// Checking 2nd list
		assert TestTools.checkCollection( rev1.getReferences2() );
		assert TestTools.checkCollection( rev2.getReferences2() );
		assert TestTools.checkCollection( rev3.getReferences2() );
		assert TestTools.checkCollection( rev4.getReferences2(), ite2_2 );
	}
