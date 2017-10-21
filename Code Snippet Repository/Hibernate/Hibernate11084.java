	@Test
	public void testAssociationQuery() {

		final AuditReader auditReader = getAuditReader();
		final Car result1 = (Car) auditReader.createQuery().forEntitiesAtRevision( Car.class, 1 ).traverseRelation( "owner", JoinType.INNER )
				.add( AuditEntity.property( "name" ).like( "Ford%" ) ).getSingleResult();
		assertEquals( "Unexpected single car at revision 1", ford.getId(), result1.getId() );

		Car result2 = (Car) auditReader.createQuery().forEntitiesAtRevision( Car.class, 1 ).traverseRelation( "owner", JoinType.INNER ).traverseRelation( "address", JoinType.INNER )
				.add( AuditEntity.property( "number" ).eq( 30 ) ).getSingleResult();
		assertEquals( "Unexpected single car at revision 1", toyota.getId(), result2.getId() );

		List<Car> resultList1 = auditReader.createQuery().forEntitiesAtRevision( Car.class, 1 ).traverseRelation( "owner", JoinType.INNER )
				.add( AuditEntity.property( "age" ).ge( 30 ) ).add( AuditEntity.property( "age" ).lt( 40 ) ).up()
				.addOrder( AuditEntity.property( "make" ).asc() ).getResultList();
		assertEquals( "Unexpected number of cars for query in revision 1", 2, resultList1.size() );
		assertEquals( "Unexpected car at index 0 in revision 1", ford.getId(), resultList1.get( 0 ).getId() );
		assertEquals( "Unexpected car at index 1 in revision 2", toyota.getId(), resultList1.get( 1 ).getId() );

		Car result3 = (Car) auditReader.createQuery().forEntitiesAtRevision( Car.class, 2 ).traverseRelation( "owner", JoinType.INNER )
				.add( AuditEntity.property( "age" ).ge( 30 ) ).add( AuditEntity.property( "age" ).lt( 40 ) ).up()
				.addOrder( AuditEntity.property( "make" ).asc() ).getSingleResult();
		assertEquals( "Unexpected car at revision 2", ford.getId(), result3.getId() );
	}
