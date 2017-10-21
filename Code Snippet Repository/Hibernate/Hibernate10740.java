	@Test
	public void testHistoryOfColl1() {
		StrTestEntity str1 = getEntityManager().find( StrTestEntity.class, str1_id );
		StrTestEntity str2 = getEntityManager().find( StrTestEntity.class, str2_id );

		MapUniEntity rev1 = getAuditReader().find( MapUniEntity.class, coll1_id, 1 );
		MapUniEntity rev2 = getAuditReader().find( MapUniEntity.class, coll1_id, 2 );
		MapUniEntity rev3 = getAuditReader().find( MapUniEntity.class, coll1_id, 3 );
		MapUniEntity rev4 = getAuditReader().find( MapUniEntity.class, coll1_id, 4 );

		assert rev1.getMap().equals( TestTools.makeMap( "1", str1 ) );
		assert rev2.getMap().equals( TestTools.makeMap( "1", str1, "2", str2 ) );
		assert rev3.getMap().equals( TestTools.makeMap( "1", str1, "2", str1 ) );
		assert rev4.getMap().equals( TestTools.makeMap( "2", str1 ) );

		assert "coll1".equals( rev1.getData() );
		assert "coll1".equals( rev2.getData() );
		assert "coll1".equals( rev3.getData() );
		assert "coll1".equals( rev4.getData() );
	}
