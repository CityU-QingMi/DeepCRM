	@Test
	public void testOneToManyCollectionSemantics() {
		final CollectionRefEdEntity edVer1 = new CollectionRefEdEntity( 1, "data_ed_1" );
		final CollectionRefIngEntity ingVer1 = new CollectionRefIngEntity( 2, "data_ing_1" );
		final CollectionRefIngEntity ingVer2 = new CollectionRefIngEntity( 2, "modified data_ing_1" );

		CollectionRefEdEntity entity = getAuditReader().find( CollectionRefEdEntity.class, 1, 18 );

		Assert.assertEquals( edVer1, entity );
		Assert.assertTrue( TestTools.checkCollection( entity.getReffering(), ingVer1 ) );

		entity = getAuditReader().find( CollectionRefEdEntity.class, 1, 19 );

		Assert.assertTrue( TestTools.checkCollection( entity.getReffering(), ingVer2 ) );

		List queryResult = getAuditReader().createQuery().forRevisionsOfEntity( CollectionRefEdEntity.class, false, true )
				.add( AuditEntity.id().eq( 1 ) )
				.add( AuditEntity.revisionType().eq( RevisionType.DEL ) )
				.getResultList();
		Object[] objArray = (Object[]) queryResult.get( 0 );

		Assert.assertEquals( 20, getRevisionNumber( objArray[1] ) );

		entity = (CollectionRefEdEntity) objArray[0];
		Assert.assertEquals( "data_ed_1", entity.getData() );
		Assert.assertTrue( TestTools.checkCollection( entity.getReffering(), ingVer2 ) );
	}
