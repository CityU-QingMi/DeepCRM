	@Test
	public void testReferencedOneToManySameRevision() {
		List queryResult = getAuditReader().createQuery().forRevisionsOfEntity( SetRefIngEntity.class, false, true )
				.add( AuditEntity.id().eq( 2 ) )
				.add( AuditEntity.revisionType().eq( RevisionType.DEL ) )
				.getResultList();
		Object[] objArray = (Object[]) queryResult.get( 0 );

		Assert.assertEquals( 2, getRevisionNumber( objArray[1] ) );

		SetRefIngEntity refIngEntity = (SetRefIngEntity) objArray[0];
		Assert.assertEquals( "Example Data 1", refIngEntity.getData() );

		Hibernate.initialize( refIngEntity.getReference() );
		Assert.assertEquals( "Demo Data 1", refIngEntity.getReference().getData() );
	}
