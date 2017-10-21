	public void assertOnlyOneSelect(Criteria criteria) {
		sessionFactory().getStatistics().setStatisticsEnabled( true );
		sessionFactory().getStatistics().clear();

		try {
			List<Cat> cats = criteria.list();

			assertEquals( 5, cats.size() );

			for ( Cat cat : cats ) {
				assertEquals( 3, cat.kittens.size() );

				for ( Kitten kitten : cat.kittens ) {
					assertNotNull( kitten.cat );
				}
			}

			assertEquals( "too many statements generated", 1, sessionFactory().getStatistics().getPrepareStatementCount() );
		}
		finally {
			sessionFactory().getStatistics().setStatisticsEnabled( false );
		}
	}
