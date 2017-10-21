	@Test
	public void testDisjunctionOfPropertiesFromDifferentEntities() {
		AuditReader auditReader = getAuditReader();
		// all cars where the owner has an age of 20 or lives in an address with number 30.
		List<Car> resultList = auditReader.createQuery()
				.forEntitiesAtRevision( Car.class, 1 )
				.traverseRelation( "owner", JoinType.INNER, "p" )
				.traverseRelation( "address", JoinType.INNER, "a" )
				.up().up().add( AuditEntity.disjunction().add(AuditEntity.property( "p", "age" )
						.eq( 20 ) ).add( AuditEntity.property( "a", "number" ).eq( 30 ) ) )
				.addOrder( AuditEntity.property( "make" ).asc() ).getResultList();
		assertEquals( "Expected two cars to be returned, Toyota and VW", 2, resultList.size() );
		assertEquals( "Unexpected car at index 0", toyota.getId(), resultList.get(0).getId() );
		assertEquals( "Unexpected car at index 1", vw.getId(), resultList.get(1).getId() );
	}
