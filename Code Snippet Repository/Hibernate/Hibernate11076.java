	@Test
	public void testReferringOneToManyDifferentRevisions() {
		List queryResult = getAuditReader().createQuery().forRevisionsOfEntity( SetRefEdEntity.class, false, true )
				.add( AuditEntity.id().eq( 3 ) )
				.add( AuditEntity.revisionType().eq( RevisionType.DEL ) )
				.getResultList();
		Object[] objArray = (Object[]) queryResult.get( 0 );

		Assert.assertEquals( 5, getRevisionNumber( objArray[1] ) );

		SetRefEdEntity refEdEntity = (SetRefEdEntity) objArray[0];
		Assert.assertEquals( "Demo Data 2", refEdEntity.getData() );

		Hibernate.initialize( refEdEntity.getReffering() );
		Assert.assertTrue( refEdEntity.getReffering().isEmpty() );

		// After commit in revision four, child entity has been removed.
		queryResult = getAuditReader().createQuery().forRevisionsOfEntity( SetRefEdEntity.class, false, true )
				.add( AuditEntity.id().eq( 3 ) )
				.add( AuditEntity.revisionNumber().eq( 4 ) )
				.getResultList();
		objArray = (Object[]) queryResult.get( 0 );

		refEdEntity = (SetRefEdEntity) objArray[0];
		Assert.assertEquals( "Demo Data 2", refEdEntity.getData() );

		Hibernate.initialize( refEdEntity.getReffering() );
		Assert.assertTrue( refEdEntity.getReffering().isEmpty() );
	}
