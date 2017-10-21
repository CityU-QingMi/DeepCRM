	@Test
	public void testAuditQueryWithJoinedInheritanceSubclassPropertyProjectionWithRelationTraversal() {
		// HHH-11383
		// This test was requested by the reporter so that we have a test that shows Hibernate is
		// automatically adding "INNER JOIN EntityA_AUD" despite the fact whether the query uses
		// the traverseRelation API or not.  This test makes sure that if the SQL generation is
		// changed in the future, Envers would properly fail if so.
		List results = getAuditReader().createQuery().forEntitiesAtRevision( EntityB.class, 1 )
				.addProjection( property( "name" ) )
				.traverseRelation( "relationToC", JoinType.INNER )
				.add( property( "foo" ).like( "bar" ) )
				.getResultList();
		assertEquals( 2, results.size() );
	}
