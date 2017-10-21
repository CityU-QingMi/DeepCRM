	@Test
	public void testAssociationQueryWithProjection() {

		AuditReader auditReader = getAuditReader();

		List<Integer> list1 = auditReader.createQuery().forEntitiesAtRevision( Car.class, 2 ).traverseRelation( "owner", JoinType.INNER )
				.addProjection( AuditEntity.property( "age" ) ).addOrder( AuditEntity.property( "age" ).asc() ).getResultList();
		assertEquals( "Unexpected number of results", 3, list1.size() );
		assertEquals( "Unexpected age at index 0", Integer.valueOf( 20 ), list1.get( 0 ) );
		assertEquals( "Unexpected age at index 0", Integer.valueOf( 30 ), list1.get( 1 ) );
		assertEquals( "Unexpected age at index 0", Integer.valueOf( 40 ), list1.get( 2 ) );

		List<Address> list2 = auditReader.createQuery().forEntitiesAtRevision( Car.class, 2 ).traverseRelation( "owner", JoinType.INNER )
				.addOrder( AuditEntity.property( "age" ).asc() ).traverseRelation( "address", JoinType.INNER ).addProjection( AuditEntity.selectEntity( false ) ).getResultList();
		assertEquals( "Unexpected number of results", 3, list2.size() );
		assertEquals( "Unexpected address at index 0", address1.getId(), list2.get( 0 ).getId() );
		assertEquals( "Unexpected address at index 1", address1.getId(), list2.get( 1 ).getId() );
		assertEquals( "Unexpected address at index 2", address2.getId(), list2.get( 2 ).getId() );

		List<Address> list3 = auditReader.createQuery().forEntitiesAtRevision( Car.class, 2 ).traverseRelation( "owner", JoinType.INNER ).traverseRelation( "address", JoinType.INNER )
				.addProjection( AuditEntity.selectEntity( true ) ).addOrder( AuditEntity.property( "number" ).asc() ).getResultList();
		assertEquals( "Unexpected number of results", 2, list3.size() );
		assertEquals( "Unexpected address at index 0", address1.getId(), list3.get( 0 ).getId() );
		assertEquals( "Unexpected address at index 1", address2.getId(), list3.get( 1 ).getId() );

		List<Object[]> list4 = auditReader.createQuery().forEntitiesAtRevision( Car.class, 2 ).traverseRelation( "owner", JoinType.INNER )
				.addOrder( AuditEntity.property( "age" ).asc() ).addProjection( AuditEntity.selectEntity( false ) ).traverseRelation( "address", JoinType.INNER )
				.addProjection( AuditEntity.property( "number" ) ).getResultList();
		assertEquals( "Unexpected number of results", 3, list4.size() );
		final Object[] index0 = list4.get( 0 );
		assertEquals( "Unexpected owner at index 0", vwOwner.getId(), ( (Person) index0[0] ).getId() );
		assertEquals( "Unexpected number at index 0", Integer.valueOf( 5 ), index0[1] );
		final Object[] index1 = list4.get( 1 );
		assertEquals( "Unexpected owner at index 1", fordOwner.getId(), ( (Person) index1[0] ).getId() );
		assertEquals( "Unexpected number at index 1", Integer.valueOf( 5 ), index1[1] );
		final Object[] index2 = list4.get( 2 );
		assertEquals( "Unexpected owner at index 2", toyotaOwner.getId(), ( (Person) index2[0] ).getId() );
		assertEquals( "Unexpected number at index 2", Integer.valueOf( 30 ), index2[1] );
	}
