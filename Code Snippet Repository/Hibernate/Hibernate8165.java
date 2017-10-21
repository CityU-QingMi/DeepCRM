	@Test
	public void testImplicitTupleNotInList() {
		final String hql = "from TheEntity e where e.compositeValue not in (:p1,:p2)";
		HQLQueryPlan queryPlan = ( (SessionFactoryImplementor) sessionFactory ).getQueryPlanCache()
				.getHQLQueryPlan( hql, false, Collections.<String,Filter>emptyMap() );

		assertEquals( 1, queryPlan.getSqlStrings().length );
		System.out.println( " SQL : " + queryPlan.getSqlStrings()[0] );
		assertTrue( queryPlan.getSqlStrings()[0].contains( "<>" ) );
	}
