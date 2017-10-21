	@Test
	public void testHistoryOfColl1() {
		EntityManager entityManager = getEntityManager();
		entityManager.getTransaction().begin();
		StrTestEntity str1 = entityManager.find( StrTestEntity.class, str1_id );
		StrTestEntity str2 = entityManager.find( StrTestEntity.class, str2_id );

		entityManager.getTransaction().commit();

		SetJoinColumnRefCollEntity rev1 = getAuditReader().find( SetJoinColumnRefCollEntity.class, coll1_id, 1 );
		SetJoinColumnRefCollEntity rev2 = getAuditReader().find( SetJoinColumnRefCollEntity.class, coll1_id, 2 );
		SetJoinColumnRefCollEntity rev3 = getAuditReader().find( SetJoinColumnRefCollEntity.class, coll1_id, 3 );
		SetJoinColumnRefCollEntity rev4 = getAuditReader().find( SetJoinColumnRefCollEntity.class, coll1_id, 4 );

		assert rev1.getCollection().equals( TestTools.makeSet( str1 ) );
		assert rev2.getCollection().equals( TestTools.makeSet( str1, str2 ) );
		assert rev3.getCollection().equals( TestTools.makeSet( str2 ) );
		assert rev4.getCollection().equals( TestTools.makeSet() );

		assert "coll1".equals( rev1.getData() );
		assert "coll1".equals( rev2.getData() );
		assert "coll1".equals( rev3.getData() );
		assert "coll1".equals( rev4.getData() );
	}
