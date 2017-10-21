	@Test
	public void testAuditQueryWithJoinedInheritanceUsingWithSemanticsManyToOne() {
		List results = getAuditReader().createQuery().forEntitiesAtRevision( EntityB.class, 1 )
				.add(
						disjunction()
								.add( property( "name" ).like( "b1" ) )
								.add( property( "name" ).like( "b2" ) ) )
				.traverseRelation( "relationToC", JoinType.INNER )
				.add( property( "foo" ).like( "bar" ) )
				.getResultList();
		assertEquals( 2, results.size() );
	}
