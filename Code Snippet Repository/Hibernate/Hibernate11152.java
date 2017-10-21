	@Test
	public void testAuditQueryUsingEmbeddableNonEqualityCheck() {
		try {
			final NameInfo nameInfo = new NameInfo( "John", "Doe" );
			final AuditQuery query = getAuditReader().createQuery().forEntitiesAtRevision( Person.class, 1 );
			query.add( AuditEntity.property( "nameInfo" ).le( nameInfo ) );
		}
		catch ( Exception ex ) {
			assertTyping( AuditException.class, ex );
		}
	}
