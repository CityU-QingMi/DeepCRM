	@Test
	public void testOwningManyToManySameRevision() {
		List queryResult = getAuditReader().createQuery().forRevisionsOfEntity( SetOwnedEntity.class, false, true )
				.add( AuditEntity.id().eq( 6 ) )
				.add( AuditEntity.revisionType().eq( RevisionType.DEL ) )
				.getResultList();
		Object[] objArray = (Object[]) queryResult.get( 0 );

		Assert.assertEquals( 7, getRevisionNumber( objArray[1] ) );

		SetOwnedEntity setOwnedEntity = (SetOwnedEntity) objArray[0];
		Assert.assertEquals( "Example Data 1", setOwnedEntity.getData() );

		Hibernate.initialize( setOwnedEntity.getReferencing() );
		Assert.assertEquals(
				TestTools.makeSet( new SetOwningEntity( 5, "Demo Data 1" ) ),
				setOwnedEntity.getReferencing()
		);
	}
