	@Test
	public void testReturnMetadata() {
		HQLQueryPlan plan = createQueryPlan( "from Animal a" );
		check( plan.getReturnMetadata(), false, true );

		plan = createQueryPlan( "select a as animal from Animal a" );
		check( plan.getReturnMetadata(), false, false );

		plan = createQueryPlan( "from java.lang.Object" );
		check( plan.getReturnMetadata(), true, true );

		plan = createQueryPlan( "select o as entity from java.lang.Object o" );
		check( plan.getReturnMetadata(), true, false );
	}
