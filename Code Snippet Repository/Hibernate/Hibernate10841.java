	@Test
	public void testHistoryOfUniId1() {
		StrTestEntity str1 = getEntityManager().find( StrTestEntity.class, str1_id );

		VersionsJoinTableTestEntity rev1 = getAuditReader().find( VersionsJoinTableTestEntity.class, uni1_id, 1 );
		VersionsJoinTableTestEntity rev2 = getAuditReader().find( VersionsJoinTableTestEntity.class, uni1_id, 2 );

		assert rev1.getCollection().equals( TestTools.makeSet() );
		assert rev2.getCollection().equals( TestTools.makeSet( str1 ) );

		assert "data1".equals( rev1.getData() );
		assert "data1".equals( rev2.getData() );
	}
