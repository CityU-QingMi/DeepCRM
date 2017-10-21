	@Test
	public void testAuditQueryWithJoinedInheritanceUsingWithSemanticsToOne() {
		List results = getAuditReader().createQuery().forEntitiesAtRevision( EntityB.class, 1 )
				.add(
						disjunction()
								.add( property( "name" ).like( "b1" ) )
								.add( property( "name" ).like( "b2" ) ) )
				.traverseRelation( "relationToC", JoinType.INNER )
				.add( property( "foo" ).like( "bar" ) )
				.up()
				.traverseRelation( "relationToD", JoinType.INNER )
				.add( property( "foo" ).like( "bar" ) )
				.getResultList();
		assertEquals( 2, results.size() );
	}
