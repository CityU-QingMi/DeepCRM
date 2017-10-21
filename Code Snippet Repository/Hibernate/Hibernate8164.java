	@Test
	public void testImplicitTupleNotEquals() {
		final String hql = "from TheEntity e where e.compositeValue <> :p1";
		HQLQueryPlan queryPlan = ( (SessionFactoryImplementor) sessionFactory ).getQueryPlanCache()
				.getHQLQueryPlan( hql, false, Collections.<String,Filter>emptyMap() );

		assertEquals( 1, queryPlan.getSqlStrings().length );
		System.out.println( " SQL : " + queryPlan.getSqlStrings()[0] );
		assertTrue( queryPlan.getSqlStrings()[0].contains( "<>" ) );
	}
