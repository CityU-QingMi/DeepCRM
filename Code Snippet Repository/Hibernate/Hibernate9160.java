	@Test
	public void testHqlQueryPlanWithEnabledFilter() {
		Session s = openSession();
		QueryPlanCache cache = ( (SessionImplementor) s ).getFactory().getQueryPlanCache();

		HQLQueryPlan plan1A = cache.getHQLQueryPlan( "from Person", true, getEnabledFilters( s ) );
		HQLQueryPlan plan1B = cache.getHQLQueryPlan( "from Person", false, getEnabledFilters( s ) );

		s.enableFilter( "sex" ).setParameter( "sexCode", Character.valueOf( 'F' ) );
		HQLQueryPlan plan2A = cache.getHQLQueryPlan( "from Person", true, getEnabledFilters( s ) );
		HQLQueryPlan plan2B = cache.getHQLQueryPlan( "from Person", false, getEnabledFilters( s ) );

		s.disableFilter( "sex" );
		HQLQueryPlan plan3A = cache.getHQLQueryPlan( "from Person", true, getEnabledFilters( s ) );
		HQLQueryPlan plan3B = cache.getHQLQueryPlan( "from Person", false, getEnabledFilters( s ) );

		s.enableFilter( "sex" ).setParameter( "sexCode", Character.valueOf( 'M' ) );
		HQLQueryPlan plan4A = cache.getHQLQueryPlan( "from Person", true, getEnabledFilters( s ) );
		HQLQueryPlan plan4B = cache.getHQLQueryPlan( "from Person", false, getEnabledFilters( s ) );

		assertSame( plan1A, plan3A );
		assertSame( plan1B, plan3B );
		assertSame( plan2A, plan4A );
		assertSame( plan2B, plan4B );

		assertNotSame( plan1A, plan1B );
		assertNotSame( plan1A, plan2A );
		assertNotSame( plan1A, plan2B );
		assertNotSame( plan1B, plan2A );
		assertNotSame( plan1B, plan2B );

		s.close();
	}
