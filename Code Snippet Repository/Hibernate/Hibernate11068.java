	@Test
	public void testOwnedManyToManyDifferentRevisions() {
		List queryResult = getAuditReader().createQuery().forRevisionsOfEntity( SetOwningEntity.class, false, true )
				.add( AuditEntity.id().eq( 7 ) )
				.add( AuditEntity.revisionType().eq( RevisionType.DEL ) )
				.getResultList();
		Object[] objArray = (Object[]) queryResult.get( 0 );

		Assert.assertEquals( 9, getRevisionNumber( objArray[1] ) );

		SetOwningEntity setOwningEntity = (SetOwningEntity) objArray[0];
		Assert.assertEquals( "Demo Data 2", setOwningEntity.getData() );

		Hibernate.initialize( setOwningEntity.getReferences() );
		Assert.assertEquals(
				TestTools.makeSet( new SetOwnedEntity( 8, "Example Data 2" ) ),
				setOwningEntity.getReferences()
		);
	}
