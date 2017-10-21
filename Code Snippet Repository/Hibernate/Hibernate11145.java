	@Test
	public void testEntitiesChangedAtRevision() {
		StrIntTestEntity site1 = new StrIntTestEntity( "aBc", 10, id1 );
		StrIntTestEntity site2 = new StrIntTestEntity( "a", 20, id2 );

		List result = getAuditReader().createQuery()
				.forEntitiesModifiedAtRevision( StrIntTestEntity.class, 2 )
				.getResultList();
		RevisionType revisionType = (RevisionType) getAuditReader().createQuery().forEntitiesModifiedAtRevision(
				StrIntTestEntity.class,
				2
		)
				.addProjection( AuditEntity.revisionType() ).add( AuditEntity.id().eq( id1 ) )
				.getSingleResult();

		Assert.assertTrue( TestTools.checkCollection( result, site1, site2 ) );
		Assert.assertEquals( revisionType, RevisionType.MOD );
	}
