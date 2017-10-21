	@Test
	public void testRevisionTypeEqQuery() {
		// The query shouldn't be ordered as always, otherwise - we get an exception.
		List results = getAuditReader().createQuery()
				.forRevisionsOfEntity( StrIntTestEntity.class, true, true )
				.add( AuditEntity.id().eq( id1 ) )
				.add( AuditEntity.revisionType().eq( RevisionType.MOD ) )
				.getResultList();

		Assert.assertEquals( 3, results.size() );
		Assert.assertEquals( new StrIntTestEntity( "d", 10, id1 ), results.get( 0 ) );
		Assert.assertEquals( new StrIntTestEntity( "d", 1, id1 ), results.get( 1 ) );
		Assert.assertEquals( new StrIntTestEntity( "d", 5, id1 ), results.get( 2 ) );
	}
