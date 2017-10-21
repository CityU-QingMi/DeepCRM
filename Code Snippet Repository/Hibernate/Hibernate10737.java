	@Test
	public void testHistoryOfMap1() {
		StrTestPrivSeqEntity str1 = getEntityManager().find( StrTestPrivSeqEntity.class, str1_id );
		StrTestPrivSeqEntity str2 = getEntityManager().find( StrTestPrivSeqEntity.class, str2_id );
		IntTestPrivSeqEntity int1 = getEntityManager().find( IntTestPrivSeqEntity.class, int1_id );
		IntTestPrivSeqEntity int2 = getEntityManager().find( IntTestPrivSeqEntity.class, int2_id );

		TernaryMapEntity rev1 = getAuditReader().find( TernaryMapEntity.class, map1_id, 1 );
		TernaryMapEntity rev2 = getAuditReader().find( TernaryMapEntity.class, map1_id, 2 );
		TernaryMapEntity rev3 = getAuditReader().find( TernaryMapEntity.class, map1_id, 3 );

		assertEquals( rev1.getMap(), TestTools.makeMap( int1, str1 ) );
		assertEquals( rev2.getMap(), TestTools.makeMap( int1, str1, int2, str2 ) );
		assertEquals( rev3.getMap(), TestTools.makeMap( int2, str1 ) );
	}
