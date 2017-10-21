	@Test
	@TestForIssue(jiraKey = "")
	@SuppressWarnings("")
	public void testQueryingWithProxyObject() {
		StrTestEntity originalSte = new StrTestEntity( "data", id );
		// Load the proxy instance
		StrTestEntity proxySte = (StrTestEntity) getSession().load( StrTestEntity.class, id );

		Assert.assertTrue( getAuditReader().isEntityClassAudited( proxySte.getClass() ) );

		StrTestEntity ste = getAuditReader().find( proxySte.getClass(), proxySte.getId(), 1 );
		Assert.assertEquals( originalSte, ste );

		List<Number> revisions = getAuditReader().getRevisions( proxySte.getClass(), proxySte.getId() );
		Assert.assertEquals( Arrays.asList( 1 ), revisions );

		List<StrTestEntity> entities = getAuditReader().createQuery()
				.forEntitiesAtRevision( proxySte.getClass(), 1 )
				.getResultList();
		Assert.assertEquals( Arrays.asList( originalSte ), entities );

		ste = (StrTestEntity) getAuditReader().createQuery()
				.forRevisionsOfEntity( proxySte.getClass(), true, false )
				.getSingleResult();
		Assert.assertEquals( originalSte, ste );

		ste = (StrTestEntity) getAuditReader().createQuery()
				.forEntitiesModifiedAtRevision( proxySte.getClass(), 1 )
				.getSingleResult();
		Assert.assertEquals( originalSte, ste );

	}
