	@Test
	public void testHistoryOfColl1() {
		StrTestEntity str1 = getEntityManager().find( StrTestEntity.class, str1_id );
		StrTestEntity str2 = getEntityManager().find( StrTestEntity.class, str2_id );

		SetRefCollEntity rev1 = getAuditReader().find( SetRefCollEntity.class, coll1_id, 1 );
		SetRefCollEntity rev2 = getAuditReader().find( SetRefCollEntity.class, coll1_id, 2 );
		SetRefCollEntity rev3 = getAuditReader().find( SetRefCollEntity.class, coll1_id, 3 );
		SetRefCollEntity rev4 = getAuditReader().find( SetRefCollEntity.class, coll1_id, 4 );

		assert rev1.getCollection().equals( TestTools.makeSet( str1 ) );
		assert rev2.getCollection().equals( TestTools.makeSet( str1, str2 ) );
		assert rev3.getCollection().equals( TestTools.makeSet( str2 ) );
		assert rev4.getCollection().equals( TestTools.makeSet() );

		assert "coll1".equals( rev1.getData() );
		assert "coll1".equals( rev2.getData() );
		assert "coll1".equals( rev3.getData() );
		assert "coll1".equals( rev4.getData() );
	}
