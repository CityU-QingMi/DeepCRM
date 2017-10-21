	@Test
	public void testRevisionsLeWithPropertyQuery() {
		List result = getAuditReader().createQuery()
				.forRevisionsOfEntity( StrIntTestEntity.class, false, true )
				.addProjection( AuditEntity.revisionNumber() )
				.add( AuditEntity.revisionNumber().le( 3 ) )
				.add( AuditEntity.property( "str1" ).eq( "a" ) )
				.getResultList();

		Assert.assertEquals( Arrays.asList( 1 ), result );
	}
