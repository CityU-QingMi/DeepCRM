	@Test
	public void testOwningManyToManyDifferentRevisions() {
		List queryResult = getAuditReader().createQuery().forRevisionsOfEntity( SetOwnedEntity.class, false, true )
				.add( AuditEntity.id().eq( 8 ) )
				.add( AuditEntity.revisionType().eq( RevisionType.DEL ) )
				.getResultList();
		Object[] objArray = (Object[]) queryResult.get( 0 );

		Assert.assertEquals( 10, getRevisionNumber( objArray[1] ) );

		SetOwnedEntity setOwnedEntity = (SetOwnedEntity) objArray[0];
		Assert.assertEquals( "Example Data 2", setOwnedEntity.getData() );

		Hibernate.initialize( setOwnedEntity.getReferencing() );
		Assert.assertTrue( setOwnedEntity.getReferencing().isEmpty() );

		// After commit in revision nine, related entity has been removed.
		queryResult = getAuditReader().createQuery().forRevisionsOfEntity( SetOwnedEntity.class, false, true )
				.add( AuditEntity.id().eq( 8 ) )
				.add( AuditEntity.revisionNumber().eq( 9 ) )
				.getResultList();
		objArray = (Object[]) queryResult.get( 0 );

		setOwnedEntity = (SetOwnedEntity) objArray[0];
		Assert.assertEquals( "Example Data 2", setOwnedEntity.getData() );

		Hibernate.initialize( setOwnedEntity.getReferencing() );
		Assert.assertTrue( setOwnedEntity.getReferencing().isEmpty() );
	}
