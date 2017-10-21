	@Test
	public void testReferringOneToManySameRevision() {
		List queryResult = getAuditReader().createQuery().forRevisionsOfEntity( SetRefEdEntity.class, false, true )
				.add( AuditEntity.id().eq( 1 ) )
				.add( AuditEntity.revisionType().eq( RevisionType.DEL ) )
				.getResultList();
		Object[] objArray = (Object[]) queryResult.get( 0 );

		Assert.assertEquals( 2, getRevisionNumber( objArray[1] ) );

		SetRefEdEntity refEdEntity = (SetRefEdEntity) objArray[0];
		Assert.assertEquals( "Demo Data 1", refEdEntity.getData() );

		Hibernate.initialize( refEdEntity.getReffering() );
		Assert.assertEquals(
				TestTools.makeSet( new SetRefIngEntity( 2, "Example Data 1" ) ),
				refEdEntity.getReffering()
		);
	}
