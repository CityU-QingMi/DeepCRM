	@Test
	public void testHistoryOfColl1() {
		StrTestEntity str1 = getEntityManager().find( StrTestEntity.class, str1_id );
		StrTestEntity str2 = getEntityManager().find( StrTestEntity.class, str2_id );

		DoubleSetRefCollEntity rev1 = getAuditReader().find( DoubleSetRefCollEntity.class, coll1_id, 1 );
		DoubleSetRefCollEntity rev2 = getAuditReader().find( DoubleSetRefCollEntity.class, coll1_id, 2 );
		DoubleSetRefCollEntity rev3 = getAuditReader().find( DoubleSetRefCollEntity.class, coll1_id, 3 );

		assert rev1.getCollection().equals( TestTools.makeSet( str1 ) );
		assert rev2.getCollection().equals( TestTools.makeSet( str1, str2 ) );
		assert rev3.getCollection().equals( TestTools.makeSet( str2 ) );

		assert rev1.getCollection2().equals( TestTools.makeSet( str2 ) );
		assert rev2.getCollection2().equals( TestTools.makeSet( str2 ) );
		assert rev3.getCollection2().equals( TestTools.makeSet( str1, str2 ) );
	}
