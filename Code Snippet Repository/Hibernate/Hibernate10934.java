	@Test
	public void testHistoryOfColl1() {
		StrTestEntity str1 = getEntityManager().find( StrTestEntity.class, str1_id );
		StrTestEntity str2 = getEntityManager().find( StrTestEntity.class, str2_id );

		ListRefCollEntity rev1 = getAuditReader().find( ListRefCollEntity.class, coll1_id, 1 );
		ListRefCollEntity rev2 = getAuditReader().find( ListRefCollEntity.class, coll1_id, 2 );
		ListRefCollEntity rev3 = getAuditReader().find( ListRefCollEntity.class, coll1_id, 3 );
		ListRefCollEntity rev4 = getAuditReader().find( ListRefCollEntity.class, coll1_id, 4 );

		assert TestTools.checkCollection( rev1.getCollection(), str1 );
		assert TestTools.checkCollection( rev2.getCollection(), str1, str2 );
		assert TestTools.checkCollection( rev3.getCollection(), str2 );
		assert TestTools.checkCollection( rev4.getCollection() );

		assert "coll1".equals( rev1.getData() );
		assert "coll1".equals( rev2.getData() );
		assert "coll1".equals( rev3.getData() );
		assert "coll1".equals( rev4.getData() );
	}
