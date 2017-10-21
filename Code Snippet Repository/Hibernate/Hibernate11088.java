	@Test
	public void testComparisonOfTwoPropertiesFromDifferentEntities() {
		AuditReader auditReader = getAuditReader();
		// the car where the owner age is equal to the owner address number.
		Car result = (Car) auditReader.createQuery()
				.forEntitiesAtRevision( Car.class, 1 )
				.traverseRelation( "owner", JoinType.INNER, "p" )
				.traverseRelation( "address", JoinType.INNER, "a" )
				.up().up().add(AuditEntity.property( "p", "age" )
						.eqProperty( "a", "number" ) ).getSingleResult();
		assertEquals( "Unexpected car returned", toyota.getId(), result.getId() );
	}
