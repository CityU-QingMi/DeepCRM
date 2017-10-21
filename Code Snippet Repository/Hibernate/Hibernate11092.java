	@Test
	public void testEntitiesWithANullRelatedIdAreNotReturnedMoreThanOnce() {
		final AuditReader auditReader = getAuditReader();
		List<Car> resultList = auditReader.createQuery()
				.forEntitiesAtRevision( Car.class, 1 )
				.traverseRelation( "owner", JoinType.LEFT, "p" )
				.up().add( AuditEntity.or( AuditEntity.property( "make" ).eq( "car3" ), AuditEntity.property( "p", "age" ).eq( 10 ) ) )
				.getResultList();
		assertEquals( "Expected car3 to be returned but only once", 1, resultList.size() );
		assertEquals( "Unexpected car at index 0", car3.getId(), resultList.get(0).getId() );
	}
