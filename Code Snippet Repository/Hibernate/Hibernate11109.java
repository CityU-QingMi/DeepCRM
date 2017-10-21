	@Test
	public void testMaximizeRevision() {
		List result = getAuditReader().createQuery()
				.forRevisionsOfEntity( StrIntTestEntity.class, false, true )
				.addProjection( AuditEntity.revisionNumber() )
				.add(
						AuditEntity.revisionNumber().maximize()
								.add( AuditEntity.property( "number" ).eq( 10 ) )
				)
				.getResultList();

		assert Arrays.asList( 2 ).equals( result );
	}
