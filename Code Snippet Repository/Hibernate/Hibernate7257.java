	@Test
	public void testNonDistinctCountOfEntityWithCompositeId() {
		// the check here is all based on whether we had commas in the expressions inside the count
		final HQLQueryPlan plan = sessionFactory().getQueryPlanCache().getHQLQueryPlan(
				"select count(o) from Order o",
				false,
				Collections.EMPTY_MAP
		);
		assertEquals( 1, plan.getTranslators().length );
		final QueryTranslator translator = plan.getTranslators()[0];
		final String generatedSql = translator.getSQLString();

		final int countExpressionListStart = generatedSql.indexOf( "count(" );
		final int countExpressionListEnd = generatedSql.indexOf( ")", countExpressionListStart );
		final String countExpressionFragment = generatedSql.substring( countExpressionListStart+6, countExpressionListEnd+1 );
		final boolean hadCommas = countExpressionFragment.contains( "," );

		// set up the expectation based on Dialect...
		final boolean expectCommas = sessionFactory().getDialect().supportsTupleCounts();

		assertEquals( expectCommas, hadCommas );
	}
