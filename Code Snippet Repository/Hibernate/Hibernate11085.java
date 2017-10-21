	@Test
	public void testAssociationQueryWithOrdering() {

		AuditReader auditReader = getAuditReader();

		List<Car> cars1 = auditReader.createQuery().forEntitiesAtRevision( Car.class, 1 ).traverseRelation( "owner", JoinType.INNER ).traverseRelation( "address", JoinType.INNER )
				.addOrder( AuditEntity.property( "number" ).asc() ).up().addOrder( AuditEntity.property( "age" ).desc() ).getResultList();
		assertEquals( "Unexpected number of results", 3, cars1.size() );
		assertEquals( "Unexpected car at index 0", ford.getId(), cars1.get( 0 ).getId() );
		assertEquals( "Unexpected car at index 1", vw.getId(), cars1.get( 1 ).getId() );
		assertEquals( "Unexpected car at index 2", toyota.getId(), cars1.get( 2 ).getId() );

		List<Car> cars2 = auditReader.createQuery().forEntitiesAtRevision( Car.class, 1 ).traverseRelation( "owner", JoinType.INNER ).traverseRelation( "address", JoinType.INNER )
				.addOrder( AuditEntity.property( "number" ).asc() ).up().addOrder( AuditEntity.property( "age" ).asc() ).getResultList();
		assertEquals( "Unexpected number of results", 3, cars2.size() );
		assertEquals( "Unexpected car at index 0", vw.getId(), cars2.get( 0 ).getId() );
		assertEquals( "Unexpected car at index 1", ford.getId(), cars2.get( 1 ).getId() );
		assertEquals( "Unexpected car at index 2", toyota.getId(), cars2.get( 2 ).getId() );

	}
