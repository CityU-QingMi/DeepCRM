	@Test
	public void testRevisionTypeNeQuery() {
		// The query shouldn't be ordered as always, otherwise - we get an exception.
		List results = getAuditReader().createQuery()
				.forRevisionsOfEntity( StrIntTestEntity.class, true, true )
				.add( AuditEntity.id().eq( id1 ) )
				.add( AuditEntity.revisionType().ne( RevisionType.MOD ) )
				.getResultList();

		Assert.assertEquals( 1, results.size() );
		Assert.assertEquals( new StrIntTestEntity( "a", 10, id1 ), results.get( 0 ) );
	}
