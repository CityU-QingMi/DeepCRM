	@Test
	public void testManyToManyCollectionSemantics() {
		final ListOwnedEntity edVer1 = new ListOwnedEntity( 1, "data_ed_1" );
		final ListOwningEntity ingVer1 = new ListOwningEntity( 2, "data_ing_1" );
		final ListOwningEntity ingVer2 = new ListOwningEntity( 2, "modified data_ing_1" );

		ListOwnedEntity entity = getAuditReader().find( ListOwnedEntity.class, 1, 21 );

		Assert.assertEquals( edVer1, entity );
		Assert.assertTrue( TestTools.checkCollection( entity.getReferencing(), ingVer1 ) );

		entity = getAuditReader().find( ListOwnedEntity.class, 1, 22 );

		Assert.assertTrue( TestTools.checkCollection( entity.getReferencing(), ingVer2 ) );

		List queryResult = getAuditReader().createQuery().forRevisionsOfEntity( ListOwnedEntity.class, false, true )
				.add( AuditEntity.id().eq( 1 ) )
				.add( AuditEntity.revisionType().eq( RevisionType.DEL ) )
				.getResultList();
		Object[] objArray = (Object[]) queryResult.get( 0 );

		Assert.assertEquals( 23, getRevisionNumber( objArray[1] ) );

		entity = (ListOwnedEntity) objArray[0];
		Assert.assertEquals( "data_ed_1", entity.getData() );
		Assert.assertTrue( TestTools.checkCollection( entity.getReferencing(), ingVer2 ) );
	}
