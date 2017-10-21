	@Test
	public void testAuditQueryUsingEmbeddableEquals() {
		final NameInfo nameInfo = new NameInfo( "John", "Doe" );
		final AuditQuery query = getAuditReader().createQuery().forEntitiesAtRevision( Person.class, 1 );
		query.add( AuditEntity.property( "nameInfo" ).eq( nameInfo ) );
		List<?> results = query.getResultList();
		assertEquals( 1, results.size() );
		final Person person = (Person) results.get( 0 );
		assertEquals( nameInfo, person.getNameInfo() );
	}
