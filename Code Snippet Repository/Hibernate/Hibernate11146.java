	@Test
	public void testEntitiesRemovedAtRevision() {
		StrIntTestEntity site1 = new StrIntTestEntity( null, null, id1 );

		List result = getAuditReader().createQuery()
				.forEntitiesModifiedAtRevision( StrIntTestEntity.class, 4 )
				.getResultList();
		RevisionType revisionType = (RevisionType) getAuditReader().createQuery().forEntitiesModifiedAtRevision(
				StrIntTestEntity.class,
				4
		)
				.addProjection( AuditEntity.revisionType() ).add( AuditEntity.id().eq( id1 ) )
				.getSingleResult();

		Assert.assertTrue( TestTools.checkCollection( result, site1 ) );
		Assert.assertEquals( revisionType, RevisionType.DEL );
	}
