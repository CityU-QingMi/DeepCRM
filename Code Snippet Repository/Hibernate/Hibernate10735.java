	@Test
	public void testHistoryOfMap2() {
		StrTestPrivSeqEntity str1 = getEntityManager().find( StrTestPrivSeqEntity.class, str1_id );
		StrTestPrivSeqEntity str2 = getEntityManager().find( StrTestPrivSeqEntity.class, str2_id );

		IntTestPrivSeqEntity int1 = getEntityManager().find( IntTestPrivSeqEntity.class, int1_id );
		IntTestPrivSeqEntity int2 = getEntityManager().find( IntTestPrivSeqEntity.class, int2_id );

		TernaryMapEntity rev1 = getAuditReader().find( TernaryMapEntity.class, map2_id, 1 );
		TernaryMapEntity rev2 = getAuditReader().find( TernaryMapEntity.class, map2_id, 2 );
		TernaryMapEntity rev3 = getAuditReader().find( TernaryMapEntity.class, map2_id, 3 );
		TernaryMapEntity rev4 = getAuditReader().find( TernaryMapEntity.class, map2_id, 4 );

		assert rev1.getMap().equals( TestTools.makeMap() );
		assert rev2.getMap().equals( TestTools.makeMap( int1, str1, int2, str1 ) );
		assert rev3.getMap().equals( TestTools.makeMap( int2, str1 ) );
		assert rev4.getMap().equals( TestTools.makeMap( int1, str2, int2, str1 ) );
	}
