	@Test
	public void testMinimizeWithPropertyEq() {
		List result = getAuditReader().createQuery()
				.forRevisionsOfEntity( StrIntTestEntity.class, false, true )
				.addProjection( AuditEntity.revisionNumber() )
				.add(
						AuditEntity.property( "number" ).minimize()
								.add( AuditEntity.property( "str1" ).eq( "a" ) )
				)
				.getResultList();

		assert Arrays.asList( 1 ).equals( result );
	}
