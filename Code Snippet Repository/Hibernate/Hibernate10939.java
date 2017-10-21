	@Test
	public void testHistoryOfColl1() {
		MulIdTestEntity str1 = getEntityManager().find( MulIdTestEntity.class, str1_id );
		MulIdTestEntity str2 = getEntityManager().find( MulIdTestEntity.class, str2_id );

		SetRefCollEntityMulId rev1 = getAuditReader().find( SetRefCollEntityMulId.class, coll1_id, 1 );
		SetRefCollEntityMulId rev2 = getAuditReader().find( SetRefCollEntityMulId.class, coll1_id, 2 );
		SetRefCollEntityMulId rev3 = getAuditReader().find( SetRefCollEntityMulId.class, coll1_id, 3 );
		SetRefCollEntityMulId rev4 = getAuditReader().find( SetRefCollEntityMulId.class, coll1_id, 4 );

		assert rev1.getCollection().equals( TestTools.makeSet( str1 ) );
		assert rev2.getCollection().equals( TestTools.makeSet( str1, str2 ) );
		assert rev3.getCollection().equals( TestTools.makeSet( str2 ) );
		assert rev4.getCollection().equals( TestTools.makeSet() );

		assert "coll1".equals( rev1.getData() );
		assert "coll1".equals( rev2.getData() );
		assert "coll1".equals( rev3.getData() );
		assert "coll1".equals( rev4.getData() );
	}
