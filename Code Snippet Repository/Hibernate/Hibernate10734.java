	@Test
	public void testHistoryOfMap1() {
		StrTestPrivSeqEntity str1 = getEntityManager().find( StrTestPrivSeqEntity.class, str1_id );
		StrTestPrivSeqEntity str2 = getEntityManager().find( StrTestPrivSeqEntity.class, str2_id );

		IntTestPrivSeqEntity int1 = getEntityManager().find( IntTestPrivSeqEntity.class, int1_id );
		IntTestPrivSeqEntity int2 = getEntityManager().find( IntTestPrivSeqEntity.class, int2_id );

		TernaryMapEntity rev1 = getAuditReader().find( TernaryMapEntity.class, map1_id, 1 );
		TernaryMapEntity rev2 = getAuditReader().find( TernaryMapEntity.class, map1_id, 2 );
		TernaryMapEntity rev3 = getAuditReader().find( TernaryMapEntity.class, map1_id, 3 );
		TernaryMapEntity rev4 = getAuditReader().find( TernaryMapEntity.class, map1_id, 4 );

		Assert.assertEquals( TestTools.makeMap( int1, str1 ), rev1.getMap() );
		Assert.assertEquals( TestTools.makeMap( int1, str2 ), rev2.getMap() );
		Assert.assertEquals( TestTools.makeMap( int1, str2 ), rev3.getMap() );
		Assert.assertEquals( TestTools.makeMap( int1, str2, int2, str2 ), rev4.getMap() );
	}
