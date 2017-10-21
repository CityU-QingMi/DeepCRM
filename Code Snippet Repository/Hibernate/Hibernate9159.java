	@Test
	public void testHqlQueryPlan() {
		Session s = openSession();
		QueryPlanCache cache = ( ( SessionImplementor ) s ).getFactory().getQueryPlanCache();
		assertTrue( getEnabledFilters( s ).isEmpty() );

		HQLQueryPlan plan1 = cache.getHQLQueryPlan( "from Person", false, getEnabledFilters( s ) );
		HQLQueryPlan plan2 = cache.getHQLQueryPlan( "from Person where name is null", false, getEnabledFilters( s ) );
		HQLQueryPlan plan3 = cache.getHQLQueryPlan( "from Person where name = :name", false, getEnabledFilters( s ) );
		HQLQueryPlan plan4 = cache.getHQLQueryPlan( "from Person where name = ?", false, getEnabledFilters( s ) );

		assertNotSame( plan1, plan2 );
		assertNotSame( plan1, plan3 );
		assertNotSame( plan1, plan4 );
		assertNotSame( plan2, plan3 );
		assertNotSame( plan2, plan4 );
		assertNotSame( plan3, plan4 );

		assertSame( plan1, cache.getHQLQueryPlan( "from Person", false, getEnabledFilters( s ) ) );
		assertSame( plan2, cache.getHQLQueryPlan( "from Person where name is null", false, getEnabledFilters( s ) ) );
		assertSame( plan3, cache.getHQLQueryPlan( "from Person where name = :name", false, getEnabledFilters( s ) ) );
		assertSame( plan4, cache.getHQLQueryPlan( "from Person where name = ?", false, getEnabledFilters( s ) ) );

		s.close();
	}
