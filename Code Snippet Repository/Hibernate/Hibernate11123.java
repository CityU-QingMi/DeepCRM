	@Test
	public void testRevisionsLtQuery() {
		List result = getAuditReader().createQuery()
				.forRevisionsOfEntity( StrIntTestEntity.class, false, true )
				.addProjection( AuditEntity.revisionNumber().distinct() )
				.add( AuditEntity.revisionNumber().lt( 3 ) )
				.getResultList();

		Assert.assertEquals( Arrays.asList( 1, 2 ), result );
	}
