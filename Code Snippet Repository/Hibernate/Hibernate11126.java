	@Test
	public void testRevisionsGtWithPropertyQuery() {
		List result = getAuditReader().createQuery()
				.forRevisionsOfEntity( StrIntTestEntity.class, false, true )
				.addProjection( AuditEntity.revisionNumber() )
				.add( AuditEntity.revisionNumber().gt( 1 ) )
				.add( AuditEntity.property( "number" ).lt( 10 ) )
				.getResultList();

		Assert.assertEquals( Arrays.asList( 3, 4 ), result );
	}
