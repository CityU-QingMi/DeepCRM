	@Test
	public void testRevisionCountQuery() {
		// The query shouldn't be ordered as always, otherwise - we get an exception.
		Object result = getAuditReader().createQuery()
				.forRevisionsOfEntity( StrIntTestEntity.class, false, true )
				.addProjection( AuditEntity.revisionNumber().count() )
				.add( AuditEntity.id().eq( id1 ) )
				.getSingleResult();

		Assert.assertEquals( Long.valueOf( 4 ), result );
	}
