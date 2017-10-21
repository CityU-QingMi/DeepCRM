	@Test
	public void testEntitiesAddedAtRevision() {
		StrIntTestEntity site1 = new StrIntTestEntity( "a", 10, id1 );
		StrIntTestEntity site2 = new StrIntTestEntity( "a", 10, id2 );
		StrIntTestEntity site3 = new StrIntTestEntity( "b", 5, id3 );

		List result = getAuditReader().createQuery().forEntitiesModifiedAtRevision(
				StrIntTestEntity.class,
				StrIntTestEntity.class.getName(),
				1
		).getResultList();
		RevisionType revisionType = (RevisionType) getAuditReader().createQuery().forEntitiesModifiedAtRevision(
				StrIntTestEntity.class,
				1
		)
				.addProjection( AuditEntity.revisionType() ).add( AuditEntity.id().eq( id1 ) )
				.getSingleResult();

		Assert.assertTrue( TestTools.checkCollection( result, site1, site2, site3 ) );
		Assert.assertEquals( revisionType, RevisionType.ADD );
	}
