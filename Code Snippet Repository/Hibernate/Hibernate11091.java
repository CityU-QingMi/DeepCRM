	@Test
	public void testEntitiesWithANullRelatedIdAreNotJoinedToOtherEntities() {
		final AuditReader auditReader = getAuditReader();
		List<Car> resultList = auditReader.createQuery()
				.forEntitiesAtRevision( Car.class, 1 )
				.traverseRelation( "owner", JoinType.LEFT, "p" )
				.up().add( AuditEntity.and( AuditEntity.property( "make" ).eq( "car3" ), AuditEntity.property( "p", "age" ).eq( 30 ) ) )
				.getResultList();
		assertTrue( "Expected no cars to be returned, because car3 does not have an owner", resultList.isEmpty() );
	}
