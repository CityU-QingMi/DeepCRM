	@Test
	public void testOwnedManyToManySameRevision() {
		List queryResult = getAuditReader().createQuery().forRevisionsOfEntity( SetOwningEntity.class, false, true )
				.add( AuditEntity.id().eq( 5 ) )
				.add( AuditEntity.revisionType().eq( RevisionType.DEL ) )
				.getResultList();
		Object[] objArray = (Object[]) queryResult.get( 0 );

		Assert.assertEquals( 7, getRevisionNumber( objArray[1] ) );

		SetOwningEntity setOwningEntity = (SetOwningEntity) objArray[0];
		Assert.assertEquals( "Demo Data 1", setOwningEntity.getData() );

		Hibernate.initialize( setOwningEntity.getReferences() );
		Assert.assertEquals(
				TestTools.makeSet( new SetOwnedEntity( 6, "Example Data 1" ) ),
				setOwningEntity.getReferences()
		);
	}
