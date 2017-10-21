	@Test
	public void testReferencedOneToManyDifferentRevisions() {
		List queryResult = getAuditReader().createQuery().forRevisionsOfEntity( SetRefIngEntity.class, false, true )
				.add( AuditEntity.id().eq( 4 ) )
				.add( AuditEntity.revisionType().eq( RevisionType.DEL ) )
				.getResultList();
		Object[] objArray = (Object[]) queryResult.get( 0 );

		Assert.assertEquals( 4, getRevisionNumber( objArray[1] ) );

		SetRefIngEntity refIngEntity = (SetRefIngEntity) objArray[0];
		Assert.assertEquals( "Example Data 2", refIngEntity.getData() );

		Hibernate.initialize( refIngEntity.getReference() );
		Assert.assertEquals( "Demo Data 2", refIngEntity.getReference().getData() );
	}
