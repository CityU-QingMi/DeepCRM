	@Test
	public void testAuditQueryWithJoinedInheritanceUsingWithSemanticsOneToOne() {
		List results = getAuditReader().createQuery().forEntitiesAtRevision( EntityB.class, 1 )
				.add(
						disjunction()
								.add( property( "name" ).like( "b1" ) )
								.add( property( "name" ).like( "b2" ) ) )
				.traverseRelation( "relationToD", JoinType.INNER )
				.add( property( "foo" ).like( "bar" ) )
				.getResultList();
		assertEquals( 2, results.size() );
	}
