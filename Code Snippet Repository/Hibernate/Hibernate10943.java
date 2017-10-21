	@Test
	public void testHistoryOfColl1() {
		StrTestEntity str1 = getEntityManager().find( StrTestEntity.class, str1_id );

		SetRefCollEntity rev1 = getAuditReader().find( SetRefCollEntity.class, coll1_id, 1 );
		SetRefCollEntity rev2 = getAuditReader().find( SetRefCollEntity.class, coll1_id, 2 );

		assert rev1.getCollection().equals( TestTools.makeSet() );
		assert rev2.getCollection().equals( TestTools.makeSet( str1 ) );

		assert "coll1".equals( rev1.getData() );
		assert "coll2".equals( rev2.getData() );
	}
