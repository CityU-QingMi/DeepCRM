	@Test
	public void testHistoryOfColl1() {
		EmbIdTestEntity str1 = getEntityManager().find( EmbIdTestEntity.class, str1_id );
		EmbIdTestEntity str2 = getEntityManager().find( EmbIdTestEntity.class, str2_id );

		SetRefCollEntityEmbId rev1 = getAuditReader().find( SetRefCollEntityEmbId.class, coll1_id, 1 );
		SetRefCollEntityEmbId rev2 = getAuditReader().find( SetRefCollEntityEmbId.class, coll1_id, 2 );
		SetRefCollEntityEmbId rev3 = getAuditReader().find( SetRefCollEntityEmbId.class, coll1_id, 3 );
		SetRefCollEntityEmbId rev4 = getAuditReader().find( SetRefCollEntityEmbId.class, coll1_id, 4 );

		assert rev1.getCollection().equals( TestTools.makeSet( str1 ) );
		assert rev2.getCollection().equals( TestTools.makeSet( str1, str2 ) );
		assert rev3.getCollection().equals( TestTools.makeSet( str2 ) );
		assert rev4.getCollection().equals( TestTools.makeSet() );

		assert "coll1".equals( rev1.getData() );
		assert "coll1".equals( rev2.getData() );
		assert "coll1".equals( rev3.getData() );
		assert "coll1".equals( rev4.getData() );
	}
