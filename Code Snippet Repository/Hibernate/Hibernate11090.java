	@Test
	public void testLeftJoinOnAuditedEntity() {
		final AuditReader auditReader = getAuditReader();
		// all cars where the owner has an age of 20 or where there is no owner at all
		List<Car> resultList = auditReader.createQuery()
				.forEntitiesAtRevision( Car.class, 1 )
				.traverseRelation( "owner", JoinType.LEFT, "p" )
				.up().add( AuditEntity.or( AuditEntity.property( "p", "age").eq( 20 ),
						AuditEntity.relatedId( "owner" ).eq( null ) ) )
				.addOrder( AuditEntity.property( "make" ).asc() ).getResultList();
		assertEquals( "The result list should have 2 results, car1 because its owner has an age of 30 and car3 because it has no owner at all", 2, resultList.size() );
		Car car0 = resultList.get(0);
		Car car1 = resultList.get(1);
		assertEquals( "Unexpected car at index 0", car2.getId(), car0.getId() );
		assertEquals( "Unexpected car at index 0", car3.getId(), car1.getId() );
	}
