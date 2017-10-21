	@Test
	public void testRevisionsGeQuery() {
		List result = getAuditReader().createQuery()
				.forRevisionsOfEntity( StrIntTestEntity.class, false, true )
				.addProjection( AuditEntity.revisionNumber().distinct() )
				.add( AuditEntity.revisionNumber().ge( 2 ) )
				.getResultList();

		Assert.assertEquals( TestTools.makeSet( 2, 3, 4 ), new HashSet( result ) );
	}
